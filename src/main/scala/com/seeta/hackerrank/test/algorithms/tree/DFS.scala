package com.seeta.hackerrank.test.algorithms.tree

/*

        1
  2          5
3   4     -         7
                8      -
            -     9

1. Write a class for a binary tree
2. [No code] Come up with a representation for the tree. Single line in string format
3. Implement a serializer method
4. Implement a deserializer method

 */

case class Node(value: Int, left: Option[Node] = None, right: Option[Node] = None)

object DFS {

  def dfs(root: Node): String = {
    def loop(acc: String, stack: Vector[Node]): String = {
      if (stack.isEmpty) acc
      else {
        val currentNode         = stack.head
        val newAccumulatedValue = s"${acc}${currentNode.value}"
        val left = currentNode.left.fold(newAccumulatedValue)(node => loop(newAccumulatedValue, node +: stack.tail))
        currentNode.right.fold(left)(node => loop(left, node +: stack.tail))
      }
    }

    loop("", Vector(root))
  }

  def constructTree(): Unit = ???

  def main(args: Array[String]) {
    val left  = Some(Node(2, Some(Node(3)), Some(Node(4))))
    val right = Some(Node(5, right = Some(Node(7, Some(Node(8, Some(Node(9))))))))
    println(dfs(Node(1, left, right)))
  }
}
