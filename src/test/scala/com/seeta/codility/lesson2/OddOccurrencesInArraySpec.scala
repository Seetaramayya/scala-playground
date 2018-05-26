package com.seeta.codility.lesson2

import com.seeta.codility.lesson2.OddOccurrencesInArray.solution
import org.scalatest.{Matchers, WordSpec}

/**
  *
  * @author Seeta (Ramayya) Vadali
  */
class OddOccurrencesInArraySpec extends WordSpec with Matchers {

  "OddOccurrencesInArraySpec" should {

    "solution" in {
      solution(Array(9, 3, 9, 3, 9, 7, 9)) shouldBe 7
      solution(Array(9, 3, 9, 3, 9, 7, 9)) shouldBe 7
      solution(Array(9, 9, 9, 9, 9, 7, 9)) shouldBe 7
      solution(Array(9, 9, 9, 9, 9, 9, 7)) shouldBe 7
      solution(Array(7)) shouldBe 7
      solution(Array(7, 9, 9, 9, 9, 9, 9)) shouldBe 7
    }

  }
}
