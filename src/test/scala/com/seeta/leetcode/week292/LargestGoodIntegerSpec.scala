package com.seeta.leetcode.week292

import com.seeta.leetcode.week292.LargestGoodInteger.largestGoodInteger
import org.scalatest.{Matchers, WordSpec}

class LargestGoodIntegerSpec extends WordSpec with Matchers {
  "LargestGoodInteger" should {
    "return correct 3 digit numbers if it exits" in {
      largestGoodInteger("6777133339") shouldBe "777"
      largestGoodInteger("6111133339") shouldBe "333"
      largestGoodInteger("2300019") shouldBe "000"
      largestGoodInteger("42352338") shouldBe ""
    }

    "throw exception if the constrains fail" in {
      intercept[IllegalArgumentException] {
        largestGoodInteger("12")
      }

      intercept[IllegalArgumentException] {
        largestGoodInteger("12s3")
      }

      intercept[IllegalArgumentException] {
        largestGoodInteger((1 to 1001).mkString(""))
      }
    }
  }
}
