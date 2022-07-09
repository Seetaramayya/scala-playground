package com.seeta.codility.lesson3

/** An array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)], which
  * means that exactly one element is missing.
  *
  * https://app.codility.com/programmers/lessons/3-time_complexity/perm_missing_elem/
  *
  * @author
  *   Seeta (Ramayya) Vadali
  */
object PermMissingElem {

  // TODO: Score: 80% (Correct : 100%, performance: 60%): O(N)
  // Results: https://app.codility.com/demo/results/trainingMJKUCB-94A/
  def solution1(a: Array[Int]): Int = {
    val sumOfN: Int => Long = n => (n * (n + 1)) / 2
    val total: Long         = a.sum
    (sumOfN(a.length + 1) - total).toInt
  }

  // Score: 70% (Correct : 100%, performance: 40%) : O(N**2)
  // Results : https://app.codility.com/demo/results/trainingXCK42A-GW4/
  def solution(a: Array[Int]): Int = {
    if (a.isEmpty) 1
    else {
      val n = a.length
      (1 to n + 1).find(!a.contains(_)).get
    }
  }
}
