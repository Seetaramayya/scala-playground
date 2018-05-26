package com.seeta.hackerrank.test.algorithms.implementation

/**
  * ==Problem==
  * At HackerLand University, a passing grade is any grade 40 points or higher on a 100 point scale.
  * Sam is a professor at the university and likes to round each student’s grade according to the following rules:
  *
  * <ul>
  *  <li>If the difference between the grade and the next higher multiple of 5 is less than 3,
  * round to the next higher multiple of 5 </li>
  *  <li>If the grade is less than 38, don’t bother as it’s still a failing grade
  * Automate the rounding process then round a list of grades and print the results.</li>
  *
  * ==Constraints==
  * {{{
  *   1 <= n <= 60
  *   0 <= grade <= 100
  * }}}
  *
  *
  * @author Seeta (Ramayya) Vadali
  */
object GradingStudents {
  def gradingRound(grades: Array[Int]): Array[Int] = {
    grades.map {
      case marks if (marks + 1) % 5 == 0 && marks >= 39 => marks + 1
      case marks if (marks + 2) % 5 == 0 && marks >= 38 => marks + 2
      case marks => marks
    }
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in)
    val n = sc.nextInt()
    val grades = new Array[Int](n)
    for(grades_i <- 0 until n) {
      grades(grades_i) = sc.nextInt()
    }

    gradingRound(grades)

  }
}
