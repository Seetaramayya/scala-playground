package com.seeta.traits

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StackableModificationsSpec extends AnyWordSpec with Matchers {
  "Stackable Modifications" should {
    "verify filtering before incrementing modification applied to queue" in {
      val queue = StackableModifications.filterAndIncrementQueue
      queue.put(-1)
      queue.put(0)
      queue.put(1)
      queue.get() shouldBe 1
      queue.get() shouldBe 2
    }

    "verify incrementing before filtering modification applied to queue" in {
      val queue = StackableModifications.incrementAndFilterQueue
      queue.put(-1)
      queue.put(0)
      queue.put(1)
      queue.get() shouldBe 0
      queue.get() shouldBe 1
      queue.get() shouldBe 2
    }
  }

}
