package com.seeta.codility.lesson3

import org.scalatest.{Matchers, WordSpec}

/**
  *
  * @author Seeta (Ramayya) Vadali
  */
class PermMissingElemSpec extends WordSpec with Matchers {
  "PermMissingElemSpec" should {
    "solution" in {
      PermMissingElem.solution(Array(1, 2, 3, 5)) shouldBe 4

      val input: Array[Int] = (2 to 100000).toArray
      PermMissingElem.solution(input) shouldBe 1

      //TODO: Write corner overflow test case
    }
  }
}
