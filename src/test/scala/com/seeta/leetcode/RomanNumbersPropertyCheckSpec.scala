package com.seeta.leetcode

import com.seeta.leetcode.RomanNumbers.{CharWeights, romanToInt}
import org.scalacheck.Gen
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

// https://www.scalatest.org/user_guide/generator_driven_property_checks
class RomanNumbersPropertyCheckSpec extends ScalaCheckPropertyChecks with AnyWordSpecLike with Matchers {
  val validRomanChars: Gen[Char] = Gen.oneOf(RomanNumbers.AllowedCharacters)
  val validRomanNumbers: Gen[String] = for {
    c <- validRomanChars
    n <- Gen.choose(1, 15)
    s <- Gen.const(c.toString * n)
  } yield s


  "RomanNumbersPropertyCheck" should {
    "return times of the weight if the string has same characters" in {
      forAll(validRomanNumbers) { romanNumber =>
        romanToInt(romanNumber) shouldBe CharWeights(romanNumber.head) * romanNumber.length
      }
    }
  }
}
