package com.seeta.leetcode

import com.seeta.leetcode.RomanNumbers._
import org.scalatest.{Matchers, WordSpec}

class BasicRomanNumbersSpec extends WordSpec with Matchers {
  "Roman numbers string to int conversion" should {
    "return correct" in {
      romanToInt("I") shouldBe 1
      romanToInt("II") shouldBe 2
      romanToInt("III") shouldBe 3
      romanToInt("IV") shouldBe 4
      romanToInt("VI") shouldBe 6
      romanToInt("LVIII") shouldBe 58
      romanToInt("MCMXCIV") shouldBe 1994
    }

    "throw exception in case of invalid length" in {
      intercept[IllegalArgumentException] {
        romanToInt("")
      }

      // 16 chars or above
      intercept[IllegalArgumentException] {
        romanToInt("I" * 16)
      }
    }
  }

  "Roman numbers int to string conversion" should {
    "return correct" in {
      intToRoman(1) shouldBe "I"
      intToRoman(2) shouldBe "II"
      intToRoman(3) shouldBe "III"
      intToRoman(4) shouldBe "IV"
      intToRoman(5) shouldBe "V"
      intToRoman(6) shouldBe "VI"
      intToRoman(8) shouldBe "VIII"
      intToRoman(9) shouldBe "IX"
      intToRoman(10) shouldBe "X"
      intToRoman(11) shouldBe "XI"
      intToRoman(19) shouldBe "XIX"
      intToRoman(58) shouldBe "LVIII"
      intToRoman(40) shouldBe "LC"
      intToRoman(90) shouldBe "XC"
      intToRoman(99) shouldBe "XCIX"
      intToRoman(1994) shouldBe "MCMXCIV"
    }
  }
}
