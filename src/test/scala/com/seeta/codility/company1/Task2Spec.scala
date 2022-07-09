package com.seeta.codility.company1

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** @author
  *   Seeta (Ramayya) Vadali
  */
class Task2Spec extends AnyWordSpec with Matchers {
  "Task2" should {
    "blah" in {
      Task2.solution(Array(1, 3, -3)) shouldBe 6
      Task2.solution(Array(8, 2, 4, 9, 5, 8, 0, 3, 8, 2)) shouldBe 24

      val input = Array(1000000000, 1000000000, 1000000000)
      // TODO: see it is not working
      // Task2.solution(input) shouldBe Task2.solution1(input)
    }
  }
}
