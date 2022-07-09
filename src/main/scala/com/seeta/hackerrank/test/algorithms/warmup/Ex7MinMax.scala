package com.seeta.hackerrank.test.algorithms.warmup

/** @author
  *   Seeta (Ramayya) Vadali
  */
object Ex7MinMax {
  def miniMaxSum(arr: Array[Int]): (Long, Long) = {
    val total: Long = arr.map(_.toLong).sum
    arr.map(total - _).foldLeft((Long.MaxValue, Long.MinValue)) {
      case ((min, max), x) if x < min && x > max => (x, x)
      case ((min, max), x) if x < min            => (x, max)
      case ((min, max), x) if x > max            => (min, x)
      case ((min, max), x)                       => (min, max)
    }
  }

  def main(args: Array[String]) {
    val sc  = new java.util.Scanner(System.in)
    val arr = new Array[Int](5)
    for (arr_i <- 0 until 5) {
      arr(arr_i) = sc.nextInt()
    }
    val (min, max) = miniMaxSum(arr)
    println(s"$min $max")
  }
}
