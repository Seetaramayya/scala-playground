package com.seeta.graphs

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GraphSpec extends AnyWordSpec with Matchers with GraphTestDataHelper {
  private val graph1 = Graph.createGraph(edges)
  private val graph2 = Graph.createGraph(edges2)
  private val cyclicGraph = Graph.createGraph(cyclicEdges)
  private val islandGraph = Graph.createGraph(islandEdges)

  "Graph" should {
    "traverse in correct order with or without cyclic graphs" in {
      graph1.dfs(start = 1) shouldBe Seq(1, 2, 4, 5, 3, 7, 10, 15, 20, 25)
      graph1.bfs(start = 1) shouldBe Seq(1, 2, 5, 10, 20, 4, 3, 15, 25, 7)

      cyclicGraph.dfs(start = 1) shouldBe Seq(1, 2, 4, 5, 3, 7)
      cyclicGraph.bfs(start = 1) shouldBe Seq(1, 2, 5, 4, 3, 7)

      graph1.dfs(start = 4) shouldBe Seq(4, 2, 1, 5, 3, 7, 10, 15, 20, 25)
      graph1.bfs(start = 4) shouldBe Seq(4, 2, 1, 5, 10, 20, 3, 15, 25, 7)

      graph2.dfs(start = 1) shouldBe Seq(1, 2, 4, 5, 3, 6, 7)
      graph2.bfs(start = 1) shouldBe Seq(1, 2, 3, 4, 5, 6, 7)
    }

    "return 'true' if the given graph contains cycles" ignore {
      graph1.isCyclic shouldBe false
      graph1.isCyclic shouldBe false
      graph1.isCyclic shouldBe false
    }

    "return 'true' if the given vertices are connected otherwise false" in {
      graph1.isConnected(100, 200) shouldBe false // non existing vertices
      graph1.isConnected(7, 25) shouldBe true
      graph1.isConnected(4, 25) shouldBe true
      graph1.isConnected(15, 25) shouldBe true
      islandGraph.isConnected(4, 9) shouldBe false
      islandGraph.isConnected(3, 9) shouldBe false
      islandGraph.isConnected(10, 9) shouldBe true
      islandGraph.isConnected(11, 9) shouldBe true
      islandGraph.isConnected(9, 9) shouldBe true
    }

    "return the number of islands in the given graph " in {
      graph1.connectedComponentsCount shouldBe 1
//      graph2.connectedComponentsCount shouldBe 1
      cyclicGraph.connectedComponentsCount shouldBe 1
      Graph.mermaidRepresentationOfGraph(islandGraph)
      Graph.mermaidRepresentationOfGraph(graph2)
      islandGraph.connectedComponentsCount shouldBe 3
    }
  }
}
