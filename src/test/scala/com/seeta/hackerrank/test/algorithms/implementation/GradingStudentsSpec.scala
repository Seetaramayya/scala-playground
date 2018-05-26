package com.seeta.hackerrank.test.algorithms.implementation

import com.seeta.hackerrank.test.algorithms.implementation.GradingStudents.gradingRound
import org.scalatest.{Matchers, WordSpec}

class GradingStudentsSpec extends WordSpec with Matchers {
  "Grading Students" should {
    "work" in {
      gradingRound(Array(73, 67, 38, 33)) shouldBe Array(75, 67, 40, 33)
    }
  }
}
