package com.seeta.tree

import com.seeta.tree.Tree.{bfs, dfsInOrder, dfsPostOrder, dfsPreOrder, height}
import com.seeta.tree.TreeHelpers._
import org.scalatest.{Matchers, WordSpec}

class TreeSpec extends WordSpec with Matchers {
  "Tree height" should {
    "calculate the height of the tree" in {
      height(createTree()) shouldBe 3
      height(createLeftTree()) shouldBe 5
      height(createRightTree()) shouldBe 5
    }
  }

  "Tree traversal" should {
    "return correct BFS traversal" in {
      bfs(createTree()) shouldBe List(1, 2, 3, 4, 5, 6, 7)
      bfs(createLeftTree()) shouldBe List(1, 2, 3, 4, 5)
      bfs(createRightTree()) shouldBe List(1, 2, 3, 4, 5)
    }

    "return correct DFS pre order traversal" in {
      dfsPreOrder(createTree()) shouldBe List(1, 2, 4, 5, 3, 6, 7)
    }

    "return correct DFS in order traversal" in {
      dfsInOrder(createTree()) shouldBe List(4, 2, 5, 1, 6, 3, 7)
    }

    "return correct DFS post order traversal" in {
      dfsPostOrder(createTree()) shouldBe List(4, 5, 2, 6, 7, 3, 1)
    }
  }


}
