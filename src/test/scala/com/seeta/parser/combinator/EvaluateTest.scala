package com.seeta.parser.combinator

import org.scalatest.{Matchers, WordSpec}

class EvaluateTest extends WordSpec with Matchers {
  "Arithmetic" should {
    "bla" in {
      Evaluate("2 * (3 + 4) * 5 - (8 -  (- 10))") shouldBe 52.0
      Evaluate("2 * (3 + 4) * 5 - (8 -  (- 10))") shouldBe 52.0
    }
  }

}
