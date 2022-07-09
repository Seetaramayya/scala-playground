package com.seeta.graphs

trait GraphTestDataHelper {
  /**
   * Undirected Graph:
   *  25   10--15
   *  |    |
   * 20 -- 1 -- 2
   *       |    |
   *       5    4
   *       |
   *       3
   *       |
   *       7
   *
   * DFS (start=1): 1, 5, 3, 7, 2, 4, 10, 15, 20, 25 (or) 1, 2, 4, 5, 3, 7, 10, 15, 20, 25 etc...
   * BFS (start=1): 1, 5, 2, 3, 4, 7 (or) 1, 2, 5, 4, 3, 7 (or) 1, 2, 5, 10, 20, 4, 3, 15, 25, 7
   */
  val edges: Edges[Int] = Edges.create(List((1, 2), (1, 5), (2, 4), (5, 3), (3, 7), (1, 10), (1, 20), (10, 15), (20, 25)))

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
  val cyclicEdges: Edges[Int] = Edges.create(List((1, 2), (1, 5), (2, 4),(4, 5), (5, 3), (3, 7)))

  /**
   * Directed Graph: Simple binary tree
   *                     1
   *              2             3
   *      4           5     6          7
   *
   *  BFS: 1, 2, 3, 4, 5, 6, 7
   *  DFS: 1, 2, 4, 5, 3, 6, 7 (or) 1, 3, 7, 6, 2, 5, 4
   */
  val edges2: Edges[Int] = Edges.createDirectedEdges(List((1, 2), (1, 3), (2, 4), (2, 5), (3, 6), (3, 7)))

  /**
   * Undirected Graph:
   *   1 -- 2   9--10--11
   *   |    |
   *   5 -- 4   12
   *   |
   *   3
   *   |
   *   7
   */
  val islandEdges: Edges[Int] = Edges.create(List((1, 2), (1, 5), (2, 4), (4, 5), (5, 3), (3, 7), (9, 10), (10, 11), (12, 12)))
}
