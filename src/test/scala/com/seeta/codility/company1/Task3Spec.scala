package com.seeta.codility.company1

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/**
  *
  * @author Seeta (Ramayya) Vadali
  */
class Task3Spec extends AnyWordSpec with Matchers {
  "Task3" should {
    "blah" in {
      Task3.solution(Array(1, 3, 1, 4, 2, 5), 7, 3) shouldBe 3
    }
  }
}
