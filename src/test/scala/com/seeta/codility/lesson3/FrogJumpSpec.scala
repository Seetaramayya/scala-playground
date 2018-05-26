package com.seeta.codility.lesson3

import com.seeta.codility.lesson3.FrogJump.solution
import org.scalatest.{Matchers, WordSpec}

/**
  *
  * @author Seeta (Ramayya) Vadali
  */
class FrogJumpSpec extends WordSpec with Matchers {
  "FrogJumpSpec" should {
    "solution" in {
      solution(10, 85, 30) shouldBe 3
      solution(0, 1000000000, 1000000000) shouldBe 1
      solution(0, 1000000000, 100000000) shouldBe 10
    }
  }
}
