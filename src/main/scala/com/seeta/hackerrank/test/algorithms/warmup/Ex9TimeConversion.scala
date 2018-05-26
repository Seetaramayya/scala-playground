package com.seeta.hackerrank.test.algorithms.warmup

import java.time.LocalTime
import java.time.format.DateTimeFormatter

object Ex9TimeConversion {
  def timeConversion(s: String): String =  {
    val formatter = DateTimeFormatter.ofPattern("hh:mm:ssa")
    val parsedTime = LocalTime.parse(s, formatter)
    parsedTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in)
    val s = sc.next()
    val result = timeConversion(s)
    println(result)
  }
}
