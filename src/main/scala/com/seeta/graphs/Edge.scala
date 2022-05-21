package com.seeta.graphs

sealed trait Edge[T]
case class UnDirectedEdge[T](a: T, b: T) extends Edge[T] {
  override def toString: String = s"$a -> $b\n$b -> $a"
}

case class DirectedEdge[T](a: T, b: T) extends Edge[T] {
  override def toString: String = s"$a -> $b"
}

object Edge {
  def createUndirectedEdge[T](a: T, b: T): Edge[T] = UnDirectedEdge[T](a, b)

  def createDirectedEdge[T](a: T, b: T): Edge[T] = DirectedEdge[T](a, b)
}


case class Edges[T](private val _edges: Seq[Edge[T]]) {
  def foldLeft[U](z: U)(f: (U, Edge[T]) => U): U = _edges.foldLeft(z)(f)

  def map[U](f: Edge[T] => Edge[U]): Edges[U] = Edges(_edges.map(f))
}

object Edges {
  /**
   * Creates undirected edges by default
   * @param edges to be connected
   * @tparam T generic type parameter
   * @return the edges that represents given edges
   */
  def create[T](edges: Seq[(T, T)]): Edges[T] = Edges(edges.map((Edge.createUndirectedEdge[T] _).tupled))

  def createDirectedEdges[T](edges: Seq[(T, T)]): Edges[T] = Edges(edges.map((Edge.createDirectedEdge[T] _).tupled))
}
