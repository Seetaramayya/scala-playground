package com.seeta.leetcode

import com.seeta.leetcode.RomanNumbers.{AllowedCharacters, CharWeights, romanToInt}
import org.scalatest.prop.PropertyChecks
import org.scalacheck.Gen
import org.scalatest.{Matchers, WordSpec, WordSpecLike}

// https://www.scalatest.org/user_guide/generator_driven_property_checks
class RomanNumbersPropertyCheckSpec extends PropertyChecks with WordSpecLike with Matchers {
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
