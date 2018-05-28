package com.seeta.codility.lesson3

import org.scalatest.{Matchers, WordSpec}

/**
  *
  * @author Seeta (Ramayya) Vadali
  */
class TapeEquilibriumSpec extends WordSpec with Matchers {

  "TapeEquilibriumSpec" should {

    "solution" in {
      TapeEquilibrium.solution(Array(3, 1, 2, 4, 3)) shouldBe 1
      TapeEquilibrium.solution(Array(1, 2, 3)) shouldBe 0
      TapeEquilibrium.solution(Array(1, 2, 3, 6)) shouldBe 0
    }

  }
}
