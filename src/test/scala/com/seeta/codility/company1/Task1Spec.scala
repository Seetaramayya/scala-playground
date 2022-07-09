package com.seeta.codility.company1

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** @author
  *   Seeta (Ramayya) Vadali
  */
class Task1Spec extends AnyWordSpec with Matchers {
  "Task1" should {
    // TODO: not covering all test cases may be `property` based testing
    "blah" in {
      Task1.solution("We test coders. Give us a try?") shouldBe 4
      Task1.solution("Forget  CVs..Save time . x x") shouldBe 2
      Task1.solution("fdsdfsdfdsf erfdsfgh. sarfasfdsf?") shouldBe 2
      Task1.solution("") shouldBe 0
      Task1.solution(" 1 ") shouldBe 1
      Task1.solution("      ") shouldBe 0
    }
  }
}
