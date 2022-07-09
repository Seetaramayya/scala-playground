package com.seeta.codility.company1

/** @author
  *   Seeta (Ramayya) Vadali
  */
object Task2 {
  def solution1(a: Array[Int]): Int = {
    var max = Integer.MIN_VALUE
    for {
      p <- a.indices
      q <- p until a.length
    } {
      max = Math.max(max, a(p) + a(q) + (p - q))
    }
    max
  }

  def solution(a: Array[Int]): Int = {
    var maxSumP: Long = Integer.MIN_VALUE
    var maxSumQ: Long = Integer.MIN_VALUE

    for (i <- a.indices) {
      maxSumP = Math.max(a(i) - i, maxSumP)
      maxSumQ = Math.max(a(i) + i, maxSumQ)
    }
    (maxSumP + maxSumQ).toInt
  }
}
