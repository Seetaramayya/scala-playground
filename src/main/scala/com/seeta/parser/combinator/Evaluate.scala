package com.seeta.parser.combinator

import scala.util.parsing.combinator.JavaTokenParsers

class Evaluate extends JavaTokenParsers {
  // expr = term { + term | - term }
  def expr: Parser[Double] = term ~ rep("+" ~ term | "-" ~ term) ^^ { case (x ~ list) =>
    (x /: list) {
      case (a, "+" ~ b) => a + b
      case (a, "-" ~ b) => a - b
    }
  }

  // term = factor { * factor | / factor }
  def term: Parser[Double] = factor ~ rep("*" ~ factor | "/" ~ factor) ^^ { case number ~ list =>
    (number /: list) {
      case (x, "*" ~ y) => x * y
      case (x, "/" ~ y) => x / y
    }
  }

  def number: Parser[Double] = floatingPointNumber ^^ (_.toDouble)
  // factor = floatingPointNumber | "( expr)"
  def factor: Parser[Double] = number | unary | "(" ~> expr <~ ")"

  // unary -
  def unary: Parser[Double] = "(" ~> ("-" | "+") ~ number <~ ")" ^^ {
    case "+" ~ x => x
    case "-" ~ x => -x
  }

  def evaluate(inputExpr: String): Double = {
    parseAll(expr, inputExpr).map(x => x).getOrElse(throw new IllegalStateException("could not evaluate"))
  }
}

object Evaluate {
  def apply(inputExpression: String): Double = new Evaluate().evaluate(inputExpression)
}
