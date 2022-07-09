package com.seeta.hackerrank.test.algorithms.warmup

import java.text.DecimalFormat

/** ==Sample Input==
  * {{{
  *   6
  *   -4 3 -9 0 4 1
  * }}}
  *
  * ==Sample Output==
  * {{{
  *   0.500000
  *   0.333333
  *   0.166667
  * }}}
  *
  * @author
  *   Seeta (Ramayya) Vadali
  */
object Ex5PlusMinus extends App {
  val formatter = new DecimalFormat("##.######")
  val sc        = new java.util.Scanner(System.in)
  var n         = sc.nextInt()
  var arr       = new Array[Int](n)
  (0 until n).foreach { i =>
    arr(i) = sc.nextInt()
  }

  val (positive, zero, negative) = arr.foldLeft((0, 0, 0)) { case ((l, m, r), n) =>
    if (n > 0) (l + 1, m, r) else if (n < 0) (l, m, r + 1) else (l, m + 1, r)
  }

  val total                    = positive + zero + negative
  val positiveFraction: Double = positive.toDouble / total
  val zeroFraction: Double     = zero.toDouble / total

  val negativeFraction: Double = negative.toDouble / total

  println(formatter.format(positiveFraction))
  println(formatter.format(negativeFraction))
  println(formatter.format(zeroFraction))
}
