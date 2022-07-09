package com.seeta.tree

object TreeHelpers {

  /** 1 2 3 4 5 6 7
    */
  def createTree(): Node[Int] = {
    val left  = Node(2, Some(Node(4)), Some(Node(5)))
    val right = Node(3, Some(Node(6)), Some(Node(7)))
    Node(1, Some(left), Some(right))
  }

  /** 1 2 3 4 5
    */
  def createLeftTree(): Node[Int] = {
    Node(1, Some(Node(2, Some(Node(3, Some(Node(4, Some(Node(5)))))))))
  }

  /** 1 2 3 4 5
    */
  def createRightTree(): Node[Int] = {
    Node(1, right = Some(Node(2, right = Some(Node(3, right = Some(Node(4, right = Some(Node(5)))))))))
  }
}
