package com.seeta.hackerrank.test.algorithms.warmup

/**
  *
  * @author Seeta (Ramayya) Vadali
  */
object Ex8BirthdayCandles {
  def birthdayCakeCandles(n: Int, ar: Array[Int]): Int =  {
    val (_, numberOfCandles) = ar.foldLeft((Int.MinValue, 0)) {
      case ((max, _), current) if current > max => (current, 1)
      case ((max, occ), current) if current == max => (current, occ + 1)
      case ((max, occ), _)  => (max, occ)
    }
    numberOfCandles
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in)
    val n = sc.nextInt()
    val ar = new Array[Int](n)
    for(ar_i <- 0 until n) {
      ar(ar_i) = sc.nextInt()
    }
    val result = birthdayCakeCandles(n, ar)
    println(result)
  }
}
