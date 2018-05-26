package com.seeta.codility.lesson1

import org.scalatest.{Matchers, WordSpec}

class BinaryGapSpec extends WordSpec with Matchers {

  "BinaryGap" should {
    "produce" in {
      BinaryGap.solution(1041) shouldBe 5 //10000010001
      BinaryGap.solution(32) shouldBe 0 //100000
      BinaryGap.solution(9) shouldBe 2
      BinaryGap.solution(529) shouldBe 4
      BinaryGap.solution(15) shouldBe 0
      BinaryGap.solution(32) shouldBe 0
      BinaryGap.solution(20) shouldBe 1
    }
  }
}
