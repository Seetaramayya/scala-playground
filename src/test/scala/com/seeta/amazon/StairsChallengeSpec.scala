package com.seeta.amazon

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

import com.seeta.amazon.StairsChallenge._
import org.scalatest.{Matchers, WordSpec}

class StairsChallengeSpec extends WordSpec with Matchers {

  "StairsChallengeSpec" should {
    "return number of ways for (1, 2) steps" in {
      implicit val steps: Set[Int] = Set(1, 2)
      numberOfWays(0) shouldBe 1
      numberOfWays(1) shouldBe 1
      numberOfWays(2) shouldBe 2
      numberOfWays(3) shouldBe 3
      numberOfWays(4) shouldBe 5
      numberOfWays(5) shouldBe 8
      numberOfWays(6) shouldBe 13
    }

    "return number of ways for (2, 3) steps" in {
      implicit val steps: Set[Int] = Set(2, 3)
      numberOfWays(0) shouldBe 1
      numberOfWays(1) shouldBe 0
      numberOfWays(2) shouldBe 1
      numberOfWays(3) shouldBe 1
      numberOfWays(4) shouldBe 1
      numberOfWays(5) shouldBe 2
      numberOfWays(6) shouldBe 2
      numberOfWays(7) shouldBe 3
      numberOfWays(8) shouldBe 4
      numberOfWays(9) shouldBe 5
      numberOfWays(10) shouldBe 7
    }

    "return number of ways for (1, 4, 7) steps" in {
      implicit val steps: Set[Int] = Set(1, 4, 7)
      numberOfWays(0) shouldBe 1
      numberOfWays(1) shouldBe 1
      numberOfWays(2) shouldBe 1
      numberOfWays(3) shouldBe 1
      numberOfWays(4) shouldBe 2
      numberOfWays(5) shouldBe 3
      numberOfWays(6) shouldBe 4
      numberOfWays(7) shouldBe 6
    }

    "return number of ways for (2, 4, 6) steps" in {
      implicit val steps: Set[Int] = Set(2, 4, 6)
      numberOfWays(0) shouldBe 1
      numberOfWays(1) shouldBe 0
      numberOfWays(2) shouldBe 1
      numberOfWays(3) shouldBe 0
      numberOfWays(4) shouldBe 2
      numberOfWays(5) shouldBe 0
      numberOfWays(6) shouldBe 4
      numberOfWays(7) shouldBe 0
      numberOfWays(8) shouldBe 7
      numberOfWays(9) shouldBe 0
      numberOfWays(10) shouldBe 13
      (1 to 1000).filter(_ % 2 != 0).forall(numberOfWays(_) == 0)
    }

    "(extreme) return number of ways for (1) as steps, input is 100,000,000" in {
      implicit val steps: Set[Int] = Set(1)
      numberOfWays(0) shouldBe 1
      val goal = 100000000
      val start = LocalDateTime.now()
      //23414 ms in case of recursive call, another approach 23873 ms
      numberOfWays(goal) shouldBe 1

      println(s"total time taken for $goal is ${LocalDateTime.now().until(start, ChronoUnit.MILLIS).abs} ms")
    }

    "return number of ways for (10, 20) steps" in {
      implicit val steps: Set[Int] = Set(10, 20)
      numberOfWays(0) shouldBe 1
      (1 until 10).map(numberOfWays).forall(_ == 0)
      numberOfWays(10) shouldBe 1
    }
  }
}
