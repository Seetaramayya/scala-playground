package com.seeta.leetcode.week292

// Unfortunately this is input not in my control so, I have to live with nulls
class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  val value: Int = _value
  val left: TreeNode = _left
  val right: TreeNode = _right
}


/**
 * https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/
 *
 * Given the root of a binary tree, return the number of nodes where the value of the node is equal to
 * the average of the values in its subtree.
 *
 * ===Note:===
 *
 *  - The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
 *  - A subtree of root is a tree consisting of root and all of its descendants.
 *
 * ===Result===
 * {{{
 *   Runtime: 1036 ms, faster than 100.00% of Scala online submissions for Count Nodes Equal to Average of Subtree.
 *   Memory Usage: 105.6 MB, less than 100.00% of Scala online submissions for Count Nodes Equal to Average of Subtree.
 * }}}
 */
object Tree {
  def countAndSumOfSubTree(node: TreeNode): (Int, Int) = {
    if (node == null) (0, 0)
    else {
      val (leftCount, leftSum) = countAndSumOfSubTree(node.left)
      val (rightCount, rightSum) = countAndSumOfSubTree(node.right)
      (leftCount + 1 + rightCount, leftSum + node.value + rightSum)
    }
  }

  // 0, 8, 1, 4, 5, 6
  // 4, 8, 0, 1, 5, 6
  def printTree(node: TreeNode): Unit = {
    def loop(current: TreeNode, accumulated: List[Int]): List[Int] = {
      if (current == null) accumulated
      else {
        val left = loop(current.left, accumulated)

        loop(current.right, left :+ current.value)
      }
    }

    val result = loop(node, List())
    println(result)
  }

  def traverse(node: TreeNode): List[Int] = {
    if (node == null) List()
    else {
      val (count, sum) = countAndSumOfSubTree(node)
      val average =  sum / count
      val value = if (average == node.value && count != 0) 1 else 0
      (traverse(node.left) :+ value) ++ traverse(node.right)
    }
  }

  def averageOfSubtree(root: TreeNode): Int = {
    traverse(root).sum
  }
}
