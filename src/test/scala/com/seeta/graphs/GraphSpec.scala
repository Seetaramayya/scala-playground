package com.seeta.graphs

import com.seeta.graphs.Graph._
import org.scalatest.{Matchers, WordSpec}

class GraphSpec extends WordSpec with Matchers{
  /**
   * Undirected Graph:
   *   1 -- 2
   *   |    |
   *   5    4
   *   |
   *   3
   *   |
   *   7
   *
   * DFS (start=1): 1, 5, 3, 7, 2, 4 (or) 1, 2, 4, 5, 3, 7
   * BFS (start=1): 1, 5, 2, 3, 4, 7 (or) 1, 2, 5, 4, 3, 7 (or) 1, 2, 5, 3, 4, 7
   */
  private val edges: Edges[Int] = Edges.create(List((1, 2), (1, 5), (2, 4), (5, 3), (3, 7)))

  /**
   * Undirected Graph:
   *   1 -- 2
   *   |    |
   *   5 -- 4
   *   |
   *   3
   *   |
   *   7
   *
   * DFS (start=1): 1, 5, 3, 7, 2, 4 (or) 1, 2, 4, 5, 3, 7
   */
  private val cyclicEdges: Edges[Int] = Edges.create(List((1, 2), (1, 5), (2, 4), (5, 3), (3, 7)))

  /**
   * Directed Graph: Simple binary tree
   *                     1
   *              2             3
   *      4           5     6          7
   *
   *  BFS: 1, 2, 3, 4, 5, 6, 7
   *  DFS: 1, 2, 4, 5, 3, 6, 7 (or) 1, 3, 7, 6, 2, 5, 4
   */
  private val edges2: Edges[Int] = Edges.createDirectedEdges(List((1, 2), (1, 3), (2, 4), (2, 5), (3, 6), (3, 7)))
  "Graph" should {
    "traverse in correct order with or without cyclic graphs" in {
      println(Graph.createGraph(edges))
      dfs(start = 1, Graph.createGraph(edges)) shouldBe Seq(1, 2, 4, 5, 3, 7)
      bfs(start = 1, Graph.createGraph(edges)) shouldBe Seq(1, 2, 5, 4, 3, 7)

      dfs(start = 1, Graph.createGraph(cyclicEdges)) shouldBe Seq(1, 2, 4, 5, 3, 7)
      bfs(start = 1, Graph.createGraph(cyclicEdges)) shouldBe Seq(1, 2, 5, 4, 3, 7)

      dfs(start = 4, Graph.createGraph(edges)) shouldBe Seq(4, 2, 1, 5, 3, 7)
      bfs(start = 4, Graph.createGraph(edges)) shouldBe Seq(4, 2, 1, 5, 3, 7)

      dfs(start = 1, Graph.createGraph(edges2)) shouldBe Seq(1, 2, 4, 5, 3, 6, 7)
      bfs(start = 1, Graph.createGraph(edges2)) shouldBe Seq(1, 2, 3, 4, 5, 6, 7)
    }
  }
}
