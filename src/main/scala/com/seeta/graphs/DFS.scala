package com.seeta.graphs

import scala.annotation.tailrec
import scala.collection.immutable.{Queue, Stack}

object DFS {
  case class Graph(graph: Map[Int, Seq[Int]]) {
    override def toString: String = {
      val result = graph.toList.sortBy(_._1).map {
        case (key, value) => s"$key -> ${value.mkString("(", ",", ")")}"
      }.mkString("\n")
      s"Graph => \n$result"
    }

    def get(key: Int): Seq[Int] = graph(key)

    def optionalGet(key: Int): Option[Seq[Int]] = graph.get(key)
  }
  def createGraph(edges: Seq[(Int, Int)]): Graph = Graph {
    edges.foldLeft(Map.empty[Int, Seq[Int]]) {
      case (acc, (a, b)) =>
        val firstDirection: Seq[Int] = acc.get(a).fold(Seq(b))(_ :+ b)
        val secondDirection: Seq[Int] = acc.get(b).fold(Seq(a))(_ :+ a)
        acc + (a -> firstDirection) + (b -> secondDirection)
    }
  }

  def dfs(graph: Graph, initial: Int): Seq[Int] = {
    @tailrec
    def loop(stack: Seq[Int], visited: Set[Int], accumulator: Vector[Int]): Seq[Int] = {
      if (stack.isEmpty) accumulator
      else {
        val currentNode = stack.head
        val neighbours = graph.get(currentNode)
        val newVisited = visited + currentNode
        val nodesToBeVisited = neighbours.filterNot(newVisited.contains)
        loop(nodesToBeVisited ++ stack.tail, newVisited, accumulator :+ currentNode) // TODO: :+ is expensive check
      }
    }

    loop(Vector(initial), Set(), Vector())
  }

  def bfs(graph: Graph, initial: Int): Seq[Int] = {
    @tailrec
    def loop(queue: Queue[Int], visited: Set[Int], accumulator: Vector[Int]): Seq[Int] = {
      if (queue.isEmpty) accumulator
      else {
        val currentNode = queue.head
        val neighbours = graph.get(currentNode)
        val newVisited = visited + currentNode
        val nodesToBeVisited = neighbours.filterNot(newVisited.contains)
        val newQueue = nodesToBeVisited.foldLeft(queue.tail){ case (queue, node) => queue.enqueue(node) }
        loop(newQueue, newVisited, accumulator :+ currentNode) // TODO: :+ is expensive check
      }
    }

    loop(Queue(initial), Set(), Vector())
  }

  def main(args: Array[String]): Unit = {
    val graph = createGraph(List((1, 2), (1, 5), (2, 4), (5, 3), (3, 7)))
    val dfsTraversal = dfs(graph, 1)
    val bfsTraversal = bfs(graph, 1)
    println(s"DFS => ${dfsTraversal.mkString(",")}")
    println(s"BFS => ${bfsTraversal.mkString(",")}")

  }
}
