package com.seeta.codility.lesson3

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** @author
  *   Seeta (Ramayya) Vadali
  */
class TapeEquilibriumSpec extends AnyWordSpec with Matchers {

  "TapeEquilibriumSpec" should {

    "solution" in {
      TapeEquilibrium.solution(Array(3, 1, 2, 4, 3)) shouldBe 1
      TapeEquilibrium.solution(Array(1, 2, 3)) shouldBe 0
      TapeEquilibrium.solution(Array(1, 2, 3, 6)) shouldBe 0
    }

  }
}
