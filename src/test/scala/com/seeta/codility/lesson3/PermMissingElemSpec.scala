package com.seeta.codility.lesson3

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/**
  *
  * @author Seeta (Ramayya) Vadali
  */
class PermMissingElemSpec extends AnyWordSpec with Matchers {
  "PermMissingElemSpec" should {
    "solution" in {
      PermMissingElem.solution(Array()) shouldBe 1
      PermMissingElem.solution(Array(2)) shouldBe 1
      PermMissingElem.solution(Array(1, 2, 3, 5)) shouldBe 4

      val input: Array[Int] = (2 to 100000).toArray
      PermMissingElem.solution(input) shouldBe 1
    }

    "solution1" in {
      PermMissingElem.solution(Array()) shouldBe 1
      PermMissingElem.solution(Array(2)) shouldBe 1
      PermMissingElem.solution(Array(1, 2, 3, 5)) shouldBe 4

      val input: Array[Int] = (2 to 100000).toArray
      PermMissingElem.solution(input) shouldBe 1
    }
  }
}
