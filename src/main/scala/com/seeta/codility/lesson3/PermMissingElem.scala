package com.seeta.codility.lesson3

/**
  * https://app.codility.com/programmers/lessons/3-time_complexity/perm_missing_elem/
  * @author Seeta (Ramayya) Vadali
  */
object PermMissingElem {
  //Score: 80% (Correct : 100%, performance: 60%)
  def solution(a: Array[Int]): Int = {
    val sumOfN: Int => Long = n => (n * (n + 1)) / 2
    val total: Long = a.sum
    (sumOfN(a.length + 1) - total).toInt
  }
}
