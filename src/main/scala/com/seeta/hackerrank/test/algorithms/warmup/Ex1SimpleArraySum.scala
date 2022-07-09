package com.seeta.hackerrank.test.algorithms.warmup

/** ==Problem==
  * Given an array of integers, can you find the sum of its elements?
  *
  * ==Input Format==
  * The first line contains an integer, , denoting the size of the array. The second line contains space-separated
  * integers representing the array's elements.
  *
  * {{{
  *   6
  *   1 2 3 4 10 11
  * }}}
  *
  * ==Output Format==
  * Print the sum of the array's elements as a single integer.
  *
  * @author
  *   Seeta (Ramayya) Vadali
  */
object Ex1SimpleArraySum extends App {

  def simpleArraySum(n: Int, ar: Array[Int]): Int = {
    ar.sum
  }

  val sc = new java.util.Scanner(System.in)
  var n  = sc.nextInt()
  var ar = new Array[Int](n)
  for (ar_i <- 0 until n) {
    ar(ar_i) = sc.nextInt()
  }
  val result = simpleArraySum(n, ar)
  println(result)
}
