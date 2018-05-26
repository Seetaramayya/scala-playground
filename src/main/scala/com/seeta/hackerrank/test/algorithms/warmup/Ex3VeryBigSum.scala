package com.seeta.hackerrank.test.algorithms.warmup

import java.util.Scanner

import scala.annotation.tailrec

/**
  * You are given an array of integers of size `N`. You need to print the sum of the elements in the array,
  * keeping in mind that some of those integers may be quite large.
  *
  * ==Input Format==
  * The first line of the input consists of an integer N.
  * The next line contains N space-separated integers contained in the array.
  *
  * {{{
  *   5
  *   1000000001 1000000002 1000000003 1000000004 1000000005
  * }}}
  *
  * ==Output Format==
  * Print a single value equal to the sum of the elements in the array.
  *
  * ==Constraints==
  * 1 <= N <= 10
  * 0 <= A[i] <= 100000 00000
  *
  * @author Seeta (Ramayya) Vadali
  */
object Ex3VeryBigSum extends App {
  def aVeryBigSum(n: Int, ar: Array[Long]): Long =  {
    ar.sum
  }

  def sum(n: Int, ar: Array[Long]): Long =  {
    @tailrec
    def recursive(acc: Long, arr: Array[Long]): Long = {
      if (arr.isEmpty) acc
      else recursive(acc + arr.head, arr.tail)
    }
    recursive(0, ar)
  }

  val sc = new Scanner(System.in)
  val n = sc.nextInt()
  val arr = new Array[Long](n)

  (0 until n).foreach(arr(_) = sc.nextLong())
  println(aVeryBigSum(n, arr))
  println(sum(n, arr))
}
