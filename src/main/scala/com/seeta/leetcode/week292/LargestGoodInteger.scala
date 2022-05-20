package com.seeta.leetcode.week292

import scala.util.Try


/**
 * https://leetcode.com/problems/largest-3-same-digit-number-in-string/
 *
 * You are given a string `input` representing a large integer. An integer is good if it meets the following conditions:
 *
 *  - It is a substring of `input` with length 3.
 *  - It consists of only one unique digit.
 *
 *  Return the maximum good integer as a string or an empty string "" if no such integer exists.
 *
 *  '''Note:'''
 *  A substring is a contiguous sequence of characters within a string.
 *  There may be leading zeroes in num or a good integer.
 *
 * ===Example 1:===
 * {{{
 *   Input: num = "6777133339"
 *   Output: "777"
 *   Explanation: There are two distinct good integers: "777" and "333".
 *   "777" is the largest, so we return "777".
 * }}}
 *
 * ===Example 2:===
 * {{{
 *   Input: num = "2300019"
 *   Output: "000"
 *   Explanation: "000" is the only good integer.
 * }}}
 *
 *
 * ===Constraints===
 *
 *  - {{{3 <= input.length <= 1000}}}
 *  - `input` only consists of digits.
 *
 *  Started : 2022-05-08T07:20:54.261609Z
 *  End     : 2022-05-08T07:52:28.450830Z
 *  Duration: 32 mins
 *
 *  ===Result===
 *
 *  {{{
 *    Runtime: 1219 ms, faster than 100.00% of Scala online submissions for Largest 3-Same-Digit Number in String.
 *    Memory Usage: 94 MB, less than 100.00% of Scala online submissions for Largest 3-Same-Digit Number in String.
 *  }}}
 */
object LargestGoodInteger {

  val isCorrectLength: String => Boolean = input => input.length >= 3 && input.length <= 1000
  val isNotNumber: String => Boolean = _.replaceAll("""\d+""", "").nonEmpty
  val allCharsSame: String => Boolean = input => input.length match {
      case 0 => false
      case n if n % 2 == 0 => input == input.reverse
      case n => input == input.reverse && (input(n / 2) == input.head)
    }


  def largestGoodInteger(input: String): String = {
    if (!isCorrectLength(input) || isNotNumber(input)) {
      throw new IllegalArgumentException("Input should only contains digits and length should be in between 3 and 1000 inclusive")
    } else {
      input.sliding(3).filter(allCharsSame).toList.sorted.lastOption.getOrElse("")
    }
  }

}
