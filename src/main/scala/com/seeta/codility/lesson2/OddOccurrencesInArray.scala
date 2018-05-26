package com.seeta.codility.lesson2

import scala.collection.mutable.ArrayBuffer

/**
  * https://app.codility.com/programmers/lessons/2-arrays/odd_occurrences_in_array/
  *
  * @author Seeta (Ramayya) Vadali
  */
object OddOccurrencesInArray {
  //Correctness : 80% performance : 25% => 55%
  def solution1(a: Array[Int]): Int = {
    a.groupBy(x => x).filter {
      case (_, x) if x.length == 1 => true
      case _ => false
    }.values.head.head
  }

  // Not a good solution as well
  def solution2(a: Array[Int]): Int = {
    val acc = ArrayBuffer[Int]()
    a.foreach { elem =>
      val position = acc.indexOf(elem)
      if (position == -1) {
        acc.append(elem)
      } else {
        acc.remove(position)
      }
    }
    acc.head
  }


  def solution(a: Array[Int]): Int = a.reduce(_ ^ _)
}
