package com.seeta.leetcode.week292

import com.seeta.leetcode.week292.Tree.{averageOfSubtree, countAndSumOfSubTree, printTree}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CountNodesEqualToAverageOfSubtreeSpec extends AnyWordSpec with Matchers {
  "CountNodesEqualToAverageOfSubtree" should {
    "count number of nodes in a tree" in {
      averageOfSubtree(createTree()) shouldBe 5
    }
  }

  private def createTree(): TreeNode = {
    val left = new TreeNode(8, new TreeNode(0), new TreeNode(1))
    val right = new TreeNode(5, null, new TreeNode(6))
    new TreeNode(4, left, right)
  }

  private def createRightTree(): TreeNode = {
    new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4))))
  }
}
