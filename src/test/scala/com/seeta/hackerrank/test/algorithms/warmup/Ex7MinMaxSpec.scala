package com.seeta.hackerrank.test.algorithms.warmup

import Ex7MinMax.miniMaxSum
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Ex7MinMaxSpec extends AnyWordSpec with Matchers {
  "MinMax" should {
    "return exact min and max values" in {
      miniMaxSum(Array(1, 2, 3, 4, 5)) shouldBe (10, 14)
      miniMaxSum(Array(1, 1, 1, 1, 1)) shouldBe (4, 4)

      println(Int.MaxValue)
      val intMaxArray = Array(Int.MaxValue, Int.MaxValue, Int.MaxValue, Int.MaxValue, Int.MaxValue)
      val maxResult   = Int.MaxValue.toLong * 4
      miniMaxSum(intMaxArray) shouldBe (maxResult, maxResult)

    }
  }
}
