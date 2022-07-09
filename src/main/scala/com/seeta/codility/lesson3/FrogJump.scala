package com.seeta.codility.lesson3

/** https://app.codility.com/programmers/lessons/3-time_complexity/frog_jmp/
  * @author
  *   Seeta (Ramayya) Vadali
  */
object FrogJump {
  def solution(x: Int, y: Int, d: Int): Int = {
    val distance = y - x
    val times    = distance / d
    times + (if (distance % d == 0) 0 else 1)
  }
}
