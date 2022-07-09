package com.seeta.hackerrank.test.algorithms.warmup

/** ==Problem==
  *
  * Alice and Bob each created one problem for HackerRank. A reviewer rates the two challenges, awarding points on a
  * scale from to for three categories: problem clarity, originality, and difficulty.
  *
  * We define the rating for Alice's challenge to be the triplet (a0, a1, a2), and the rating for Bob's challenge to be
  * the triplet (b0, b1, b2).
  *
  * Your task is to find their comparison points by comparing with a0 with b0, a1 with b1, and a2 with b2. <ul> <li>If
  * ai > bi, then Alice is awarded 1 point</li> <li>If ai < bi, then Alice is awarded 1 point</li> <li>If ai = bi, then
  * neither person receives a point</li> </ul>
  *
  * Comparison points is the total points a person earned. Given A and B, can you compare the two challenges and print
  * their respective comparison points?
  *
  * ==Input Format==
  * The first line contains 3 space-separated integers, describing the respective values in triplet A. The second line
  * contains 3 space-separated integers, describing the respective values in triplet B.
  *
  * {{{
  *     5 6 7
  *     3 6 10
  * }}}
  *
  * ==Constraints==
  *
  * 1 <= ai <= 100 1 <= bi <= 100
  *
  * @author
  *   Seeta (Ramayya) Vadali
  */
object Ex2CompareTriplets extends App {
  def pointsCalculator(input: (Int, Int), acc: (Int, Int)): (Int, Int) = {
    val (ai, bi) = input
    if (ai > bi) (acc._1 + 1, acc._2) else if (ai < bi) (acc._1, acc._2 + 1) else acc
  }

  val sc           = new java.util.Scanner(System.in)
  val (a0, a1, a2) = (sc.nextInt(), sc.nextInt(), sc.nextInt())
  val (b0, b1, b2) = (sc.nextInt(), sc.nextInt(), sc.nextInt())

  var initial       = (0, 0)
  val zeroPosition  = pointsCalculator((a0, b0), initial)
  val firstPosition = pointsCalculator((a1, b1), zeroPosition)
  val (alice, bob)  = pointsCalculator((a2, b2), firstPosition)
  println(s"$alice $bob")
}
