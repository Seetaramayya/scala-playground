package com.seeta.codility.lesson2

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/**
  *
  * @author Seeta (Ramayya) Vadali
  */
class CyclicRotationSpec extends AnyWordSpec with Matchers {
  "CyclicRotationSpec" should {
    "solution" in {
      CyclicRotation.solution(Array(), 0) shouldBe Array()
      CyclicRotation.solution(Array(), 1) shouldBe Array()
      CyclicRotation.solution(Array(1, 2), 0) shouldBe Array(1, 2)
      CyclicRotation.solution(Array(3, 8, 9, 7, 6), 1) shouldBe Array(6, 3, 8, 9, 7)
      CyclicRotation.solution(Array(3, 8, 9, 7, 6), 1) shouldBe Array(6, 3, 8, 9, 7)
      CyclicRotation.solution(Array(3, 8, 9, 7, 6), 1) shouldBe Array(6, 3, 8, 9, 7)
      CyclicRotation.solution(Array(3, 8, 9, 7, 6), 1) shouldBe Array(6, 3, 8, 9, 7)
      CyclicRotation.solution(Array(3, 8, 9, 7, 6), 5) shouldBe Array(3, 8, 9, 7, 6)
    }
  }
}
