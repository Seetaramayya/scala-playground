package com.seeta.leetcode.week292

import com.seeta.leetcode.week292.CountNumberOfTexts.countTexts
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CountNumberOfTextsSpec extends AnyWordSpec with Matchers {
  "CountNumberOfTexts" should {
    "decode wrongly pressed keys to possible number of words" in {
      countTexts("2") shouldBe 1
      countTexts("22") shouldBe 2
      // aaa, ab, ba, c
      countTexts("222") shouldBe 4
      // aaadd, abdd, badd, cdd, aaae, abe, bae, ce
      "22233"
      countTexts("22233") shouldBe 8
      countTexts("222222222222222222222222222222222222") shouldBe 82876089
    }
  }
}
