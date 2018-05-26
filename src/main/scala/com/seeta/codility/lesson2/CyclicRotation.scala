package com.seeta.codility.lesson2

import scala.annotation.tailrec

/**
  * Problem: https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/
 *
  * @author Seeta (Ramayya) Vadali
  */
object CyclicRotation {
  def solution(a: Array[Int], k: Int): Array[Int] = {
    @tailrec
    def recurse(arr: Array[Int], current: Int): Array[Int] = {
      if (current == 0 || arr.isEmpty) arr
      else recurse(arr.last +: arr.init, current - 1)
    }

    recurse(a, k)
  }
}
