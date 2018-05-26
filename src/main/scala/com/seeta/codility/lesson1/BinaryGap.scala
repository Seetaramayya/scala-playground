package com.seeta.codility.lesson1


object BinaryGap {
  def solution(n: Int): Int = {
    val regex = raw"(?<=1)0+(?=1)".r
    val gaps = regex.findAllIn(n.toBinaryString).map(_.length)
    if (gaps.isEmpty) 0 else gaps.max
  }
}
