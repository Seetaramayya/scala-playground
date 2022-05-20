package com.seeta.tree

import scala.annotation.tailrec

case class Node[T](value: T, left: Option[Node[T]] = None, right: Option[Node[T]] = None) {
  // ${left.fold("")(_.toString)} ${right.fold("")(_.toString)}
  override def toString: String = s"$value"
}

object Tree {
  def height[T](tree: Node[T]): Int = {
    val leftHeight = tree.left.fold(0)(node => height(node))
    val rightHeight = tree.right.fold(0)(node => height(node))
    if (leftHeight < rightHeight) rightHeight + 1 else leftHeight + 1
  }

  def bfs[T](tree: Node[T]): List[T] = {
    @tailrec
    def loop(visitList: List[Node[T]], accumulated: List[T]): List[T] = {
      if (visitList.isEmpty) accumulated
      else {
        val current = visitList.head
        current match {
          case Node(value, None, None) => loop(visitList.tail, accumulated :+ value)
          case Node(value, Some(left), None) => loop(visitList.tail :+ left, accumulated :+ value)
          case Node(value, None, Some(right)) => loop(visitList.tail :+ right, accumulated :+ value)
          case Node(value, Some(left), Some(right)) => loop(visitList.tail :+ left :+ right, accumulated :+ value)
        }
      }
    }
    loop(List(tree), List())
  }

  /**
   *       1
   *    2     3
   * 4    5 6    7
   *
   * 1, 2, 4, 5, 3, 6, 7
   *
   * call                            stack
   * ++++++++++++++++++++++++     => +++++
   * loop(Some(1), List())        => ...
   * loop(Some(2), List(1))       => (3)
   * loop(Some(4), List(1,2)      => (5, 3)
   * loop(None,    List(1,2, 4)   => (None, 5, 3)
   *
   * |     |
   * |     |
   * | (3) |
   * -----
   */
  def dfsPreOrder[T](root: Node[T]): List[T] = {
    val value = root.value
    val left = root.left.fold(List.empty[T])(dfsPreOrder)
    val right = root.right.fold(List.empty[T])(dfsPreOrder)

    (value +: left) ++ right

//    def loop(current: Option[Node[T]], accumulated: List[T]): List[T] = {
//      println(current)
//      current match {
//        case None => accumulated
//        case Some(node) =>
//          val result = loop(node.left, accumulated :+ node.value)
//          loop(node.right, accumulated ++ result)
//      }
//    }
//
//    loop(Some(root), List())
  }

  /**
   *       1
   *    2     3
   * 4    5 6    7
   *
   * 4, 2, 5, 1, 6, 3, 7
   */
  def dfsInOrder[T](root: Node[T]): List[T] = {
    val left = root.left.fold(List.empty[T])(dfsInOrder)
    val value = root.value
    val right = root.right.fold(List.empty[T])(dfsInOrder)
    (left :+ value) ++ right
  }

  /**
   *       1
   *    2     3
   * 4    5 6    7
   *
   * 4, 5, 2, 6, 7, 3, 1
   */
  def dfsPostOrder[T](root: Node[T]): List[T] = {
    val left = root.left.fold(List.empty[T])(dfsPostOrder)
    val value = root.value
    val right = root.right.fold(List.empty[T])(dfsPostOrder)
    (left ++ right) :+ value
  }
}