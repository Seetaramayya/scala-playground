package com.seeta.hackerrank.test.algorithms.warmup

/** Given a square matrix of size N x N, calculate the absolute difference between the sums of its diagonals.
  * ==Constraints==
  * -100 <= elements in matrix <= 100
  *
  * ==Input Format==
  * First line matrix size accordingly space separated lines and columns
  * {{{
  *   3
  *   11 2 4
  *   4 5 6
  *   10 8 -12
  * }}}
  *
  * ==Output==
  * Absolute value of |sum(ai, bi) - sum(other diagnol)|
  *
  * @author
  *   Seeta (Ramayya) Vadali
  */
object Ex4DiagnolDifference extends App {
  val sc = new java.util.Scanner(System.in)
  val n  = sc.nextInt()
  val a  = Array.ofDim[Int](n, n)
  for (a_i <- 0 until n) {
    for (a_j <- 0 until n) {
      a(a_i)(a_j) = sc.nextInt()
    }
  }

  var diag1 = 0
  var diag2 = 0
  (0 until n).foreach { i =>
    diag1 += a(i)(i)
    diag2 += a(n - 1 - i)(i)
  }

  println(Math.abs(diag1 - diag2))
}
