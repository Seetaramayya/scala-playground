package com.seeta.leetcode.week292

import com.seeta.leetcode.week292.LargestGoodInteger.{isNotNumber, largestGoodInteger}
import org.scalacheck.Gen
import org.scalatest.{Matchers, WordSpecLike}
import org.scalatest.prop.PropertyChecks

class LargestGoodIntegerPropertyCheckSpec extends PropertyChecks with WordSpecLike with Matchers {
  val seed: Gen[String] = Gen.listOf(Gen.oneOf((0 to 9).map(i => s"$i$i$i").toList)).map(_.mkString(""))
  val inputNumbers: Gen[String] = Gen.oneOf(true, false).flatMap { input =>
    if (input) seed
    else Gen.numStr
  }

  val validInput: Gen[String] = inputNumbers.filter(input => input.length >= 3 && input.length <= 1000)
  val inValidInput: Gen[String] = inputNumbers.filter(input => input.length < 3 || input.length > 1000)


  "LargestGoodInteger" should {
    "return a string that should be substring of original string or empty" in {
      forAll(validInput) { input =>
        val result = largestGoodInteger(input)
        if (result.nonEmpty) {
          input.contains(result) shouldBe true
          result.length shouldBe 3
          result shouldBe result.reverse
        }
      }
    }

    "throw an exception if the input is invalid" in {
      forAll(inValidInput) { input =>
        intercept[IllegalArgumentException] {
          println(input)
          largestGoodInteger(input)
        }
      }
    }
  }
}
