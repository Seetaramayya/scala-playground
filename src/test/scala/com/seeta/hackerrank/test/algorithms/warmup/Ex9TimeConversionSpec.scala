package com.seeta.hackerrank.test.algorithms.warmup

import Ex9TimeConversion.timeConversion
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Ex9TimeConversionSpec extends AnyWordSpec with Matchers {
 "Time conversion" should {
   "work" in {
     timeConversion("07:05:45PM") shouldBe "19:05:45"
     timeConversion("12:00:00AM") shouldBe "00:00:00"
     timeConversion("11:59:59AM") shouldBe "11:59:59"
     timeConversion("11:59:59PM") shouldBe "23:59:59"
     timeConversion("12:00:00PM") shouldBe "12:00:00"
   }
 }
}
