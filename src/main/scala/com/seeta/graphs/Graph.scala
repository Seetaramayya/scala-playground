package com.seeta.graphs

import scala.annotation.tailrec
import scala.collection.immutable.Queue

// TODO: Think about true representation of the Graph.
// Map of value -> edges are correct, can we bring type safety in any way?
case class Graph[T](private val graph: Map[T, Seq[T]]) {
  def getEdges(key: T): Seq[T] = graph.getOrElse(key, Seq.empty[T])

  override def toString: String = graph.map {
    case (key, values) => s"$key --> ${values.mkString(",")}"
  }.mkString("\n")
}


object Graph {

  def apply[T](edges: Edges[T]): Graph[T] = createGraph(edges)

  /**
   * Constructs graph with given undirected edges.
   * @param edges that connects nodes, these edges are undirected
   * @tparam T generic type parameter
   * @return Graph that represents given undirected edges
   */
  def createGraph[T](edges: Edges[T]):Graph[T] = {
    def addConnection(a: T, b: T, nodes: Map[T, Seq[T]], isDirected: Boolean):Map[T, Seq[T]] = {
      val aConnections = nodes.get(a).fold(Seq(b))(b +: _)
      val bConnections = if (isDirected) Seq.empty[T] else nodes.get(b).fold(Seq(a))(a +: _)
      nodes + (a -> aConnections) + (b -> bConnections)
    }

    val graph = edges.foldLeft(Map.empty[T, Seq[T]]) {
      case (nodes, DirectedEdge(a, b)) => addConnection(a, b, nodes, isDirected = true)
      case (nodes, UnDirectedEdge(a, b)) => addConnection(a, b, nodes, isDirected = false)
    }

    // prepend operation complexity is O(1) and reverse is O(N) which is better than
    // append which translates to O(n * n -1/2) = O(n * 2)
    Graph(graph.mapValues(_.reverse))
  }

  /**
   * Mermaid is JS plugin in Intellij which converts.
   * Output of the function can be copy pasted to https://mermaid.live/edit
   *
   * TODO: check is it possible to print directly graph instead of mermaid string
   * @param graph which you would like to print
   * @tparam T type parameter of the graph
   */
  def mermaidRepresentationOfGraph[T](graph: Graph[T]): Unit = {
    val nodes = graph.graph.flatMap {
      case (key, values) => values.map(value => s"$key-->$value;")
    }.mkString("graph TD;\n", "\n", "")
    println(nodes)
  }

  /**
   * Depth first search traversal is based on stack representation. In the other words,
   *
   *  - Add initial element to the stack
   *  - Pop the element from the stack
   *     - add element to traversed elements
   *     - if the element has edges push to the stack
   *  - repeat step2 until stack is empty
   *
   * Recursive calls are also implemented via stack so it is easy to implement with recursion. It is perfectly legal to
   * use stack kind of data structure.
   * @param graph that needs to be traversed
   * @tparam T type parameter
   * @return the sequence of elements that are traversed in the order
   */
  def dfs[T](start: T, graph: Graph[T]): Seq[T] = {
    // scala vector or list works like a stack, last in first out. (or) prepends new element
    @tailrec
    def loop(stack: Seq[T], traversed: Seq[T]): Seq[T] = {
      if (stack.isEmpty) traversed.reverse
      else {
        val current = stack.head
        val neighbours = graph.getEdges(current)
        val toBeVisited = neighbours.filterNot(traversed.contains)
        loop(toBeVisited ++ stack.tail, current +: traversed)
      }
    }

    loop(Vector(start), Vector())
  }

  /**
   * Breadth first search traversal is based on queue representation. In the other words,
   *
   *  - Add initial element to the queue
   *  - take head element from the queue
   *     - add element to traversed elements
   *     - if the element has edges then append them the queue
   *  - repeat step2 until queue is empty
   *
   * @param graph that needs to be traversed
   * @tparam T type parameter
   * @return the sequence of elements that are traversed in the order
   */
  def bfs[T](start: T, graph: Graph[T]): Seq[T] = {
    @tailrec
    def loop(queue: Queue[T], traversed: Seq[T]): Seq[T] = {
      if (queue.isEmpty) traversed.reverse
      else {
        val (head: T, remaining: Queue[T]) = queue.dequeue
        val neighbours = graph.getEdges(queue.head)
        val toBeVisited: Seq[T] = neighbours.filterNot(traversed.contains)
        //TODO: why ++ not working? throwing class cast exception
        val newQueue: Queue[T] = toBeVisited.foldLeft(remaining)(_ enqueue _)
        loop(newQueue, head +: traversed)
      }
    }

    loop(Queue(start), Seq())
  }

  // TODO
  def isCyclic[T](graph: Graph[T]): Boolean = ???

  // TODO
  def isConnected[T](graph: Graph[T], start: T, destination: T): Boolean = ???
}
