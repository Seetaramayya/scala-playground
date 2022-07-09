package com.seeta.codility.lesson3

import com.seeta.codility.lesson3.FrogJump.solution
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** @author
  *   Seeta (Ramayya) Vadali
  */
class FrogJumpSpec extends AnyWordSpec with Matchers {
  "FrogJumpSpec" should {
    "solution" in {
      solution(10, 85, 30) shouldBe 3
      solution(0, 1000000000, 1000000000) shouldBe 1
      solution(0, 1000000000, 100000000) shouldBe 10
    }
  }
}
