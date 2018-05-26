package com.seeta.hackerrank.test.algorithms.warmup

/**
  * ==Sample Input==
  * {{{
  *   6
  * }}}
  *
  * ==Sample Output==
  * {{{
  *       #
  *      ##
  *     ###
  *    ####
  *   #####
  *  ######
  * }}}
  * @author Seeta (Ramayya) Vadali
  */
object Ex6Staircase {
  def staircase(n: Int): Unit =  {
    def prependSpace(i: Int): String = " " * (n - i) + "#" * i
    val result = (1 to n).map(prependSpace) //O(n)
    result.foreach(println) // O(n)
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in)
    val n = sc.nextInt()
    staircase(n)
  }
}
