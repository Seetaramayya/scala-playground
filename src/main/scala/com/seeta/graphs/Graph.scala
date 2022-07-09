package com.seeta.graphs

import scala.annotation.tailrec
import scala.collection.immutable.Queue

trait Graph[T] {
  def graph: Map[T, Seq[T]]
  def bfs(start: T): Seq[T]
  def dfs(start: T): Seq[T]
  def isCyclic: Boolean
  def isConnected(src: T, des: T): Boolean
  def connectedComponentsCount: Int
}

// TODO: Think about true representation of the Graph.
// Map of value -> edges are correct, can we bring type safety in any way?
class DefaultGraph[T](val graph: Map[T, Seq[T]]) extends Graph[T] {
  def getEdges(key: T): Seq[T] = graph.getOrElse(key, Seq.empty[T])

  private lazy val vertices = graph.keys.toList
  private lazy val start: T = graph.head._1

  override def toString: String = graph
    .map { case (key, values) =>
      s"$key --> ${values.mkString(",")}"
    }
    .mkString("\n")

  def isCyclic(maybeStart: Option[T] = graph.headOption.map(_._1)): Boolean = {
    @tailrec
    def loop(stack: Seq[T], visited: Set[T], result: Boolean): Boolean = {
      if (stack.isEmpty || result) result
      else {
        val current = stack.head
        val neighbours = getEdges(current)
        val alreadyVisited = neighbours.toSet.intersect(visited)
        loop(
          neighbours ++ stack.tail,
          visited + current,
          result = alreadyVisited.nonEmpty
        )
      }
    }

    maybeStart match {
      case Some(start) if graph.keys.exists(_ == start) =>
        loop(Seq(start), Set(), result = false)
      case _ if graph.nonEmpty => loop(Seq(start), Set(), result = false)
      case _                   => false
    }
  }

  private def contains(key: T): Boolean = vertices.contains(key)

  def isConnected(src: T, des: T): Boolean = {
    @tailrec
    def loop(stack: Seq[T], visited: Set[T], hasPath: Boolean): Boolean = {
      if (hasPath) hasPath
      else if (stack.isEmpty) false
      else {
        val current = stack.head
        val newVisited = visited + current
        val neighboursToVisit = getEdges(current).filterNot(newVisited.contains)
        loop(neighboursToVisit ++ stack.tail, newVisited, current == des)
      }
    }

    if (!contains(src) || !contains(des)) false
    else loop(Seq(src), Set(), hasPath = false)
  }

  /** Depth first search traversal is based on stack representation. In the other words,
    *
    *  - Add initial element to the stack
    *  - Pop the element from the stack
    *     - add element to traversed elements
    *     - if the element has edges push to the stack
    *  - repeat step2 until stack is empty
    *
    * Recursive calls are also implemented via stack so it is easy to implement with recursion. It is perfectly legal to
    * use stack kind of data structure.
    * @return the sequence of elements that are traversed in the order
    */
  def dfs(start: T): Seq[T] = {
    // scala vector or list works like a stack, last in first out. (or) prepends new element
    @tailrec
    def loop(stack: Seq[T], traversed: Seq[T]): Seq[T] = {
      if (stack.isEmpty) traversed.reverse
      else {
        val current = stack.head
        val neighbours = getEdges(current)
        val visited =
          if (traversed.contains(current)) traversed else current +: traversed
        val toBeVisited = neighbours.filterNot(visited.contains)
        loop(toBeVisited ++ stack.tail, visited)
      }
    }

    loop(Vector(start), Vector())
  }

  /** Breadth first search traversal is based on queue representation. In the other words,
    *
    *  - Add initial element to the queue
    *  - take head element from the queue
    *     - add element to traversed elements
    *     - if the element has edges then append them the queue
    *  - repeat step2 until queue is empty
    *
    * @return the sequence of elements that are traversed in the order
    */
  def bfs(start: T): Seq[T] = {
    @tailrec
    def loop(queue: Queue[T], traversed: Seq[T]): Seq[T] = {
      if (queue.isEmpty) traversed.reverse
      else {
        val (current: T, remaining: Queue[T]) = queue.dequeue
        val neighbours = getEdges(queue.head)
        val visited =
          if (traversed.contains(current)) traversed else current +: traversed
        val toBeVisited: Seq[T] = neighbours.filterNot(traversed.contains)
        //TODO: why ++ not working? throwing class cast exception
        val newQueue: Queue[T] = toBeVisited.foldLeft(remaining)(_ enqueue _)
        loop(newQueue, visited)
      }
    }

    loop(Queue(start), Seq())
  }

  override def isCyclic: Boolean = ???

  /** This function returns the number of connected components. In other words, this calculates number of islands in the
    * graph.
    *
    * This only works for the undirected graph. TODO: what about directed graph?
    * @return the number of connected components
    */
  override def connectedComponentsCount: Int = {
    val (visited, count) = vertices.foldLeft((Set.empty[T], 0)) {
      case ((visited, count), vertex) if !visited.contains(vertex) =>
        ((visited ++ dfs(vertex), count + 1))
      case ((visited, count), vertex) if visited.contains(vertex) =>
        ((visited, count))
    }
    assert(visited.size == vertices.size, "Failed to visit all the vertices")
    count
  }
}

object Graph {

  def apply[T](edges: Edges[T]): Graph[T] = createGraph(edges)

  /** Constructs graph with given undirected edges.
    * @param edges that connects nodes, these edges are undirected
    * @tparam T generic type parameter
    * @return Graph that represents given undirected edges
    */
  def createGraph[T](edges: Edges[T]): Graph[T] = {
    def addConnection(
        a: T,
        b: T,
        nodes: Map[T, Seq[T]],
        isDirected: Boolean
    ): Map[T, Seq[T]] = {
      val aConnections = nodes.get(a).fold(Seq(b))(b +: _)
      val bConnections =
        if (isDirected) Seq.empty[T] else nodes.get(b).fold(Seq(a))(a +: _)
      nodes + (a -> aConnections) + (b -> bConnections)
    }

    val graph = edges.foldLeft(Map.empty[T, Seq[T]]) {
      case (nodes, DirectedEdge(a, b)) =>
        addConnection(a, b, nodes, isDirected = true)
      case (nodes, UnDirectedEdge(a, b)) =>
        addConnection(a, b, nodes, isDirected = false)
    }

    // prepend operation complexity is O(1) and reverse is O(N) which is better than
    // append which translates to O(n * n -1/2) = O(n * 2)
    new DefaultGraph(graph.view.mapValues(_.reverse).toMap)
  }

  /** Mermaid is JS plugin in Intellij which converts.
    * Output of the function can be copy pasted to https://mermaid.live/edit
    *
    * TODO: check is it possible to print directly graph instead of mermaid string
    * @param graph which you would like to print
    * @tparam T type parameter of the graph
    */
  def mermaidRepresentationOfGraph[T](graph: Graph[T]): Unit = {
    val nodes = graph.graph
      .flatMap { case (key, values) =>
        values.map(value => s"$key-->$value;")
      }
      .mkString("graph TD;\n", "\n", "")
    println(nodes)
  }
}
