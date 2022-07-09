package com.seeta.parser.combinator

import java.nio.file.{Files, Paths}

import scala.util.parsing.combinator.JavaTokenParsers

class Json extends JavaTokenParsers {
  def value: Parser[Any] = (obj
    | array
    | stringLiteral
    | floatingPointNumber ^^ (_.toDouble)
    | "null" ^^ (_ => null)
    | "true" ^^ (_ => true)
    | "false" ^^ (_ => false))

  def obj: Parser[Map[String, Any]] = "{" ~> repsep(member, ",") <~ "}" ^^ (_.toMap)

  def array: Parser[List[Any]] = "[" ~> repsep(value, ",") <~ "]"

  def member: Parser[(String, Any)] = (stringLiteral ~ ":" ~ value) ^^ { case name ~ ":" ~ value =>
    (name, value)
  }
}

object JsonParser extends Json {
  def main(args: Array[String]): Unit = {
    val bytes = Files.readAllBytes(Paths.get(JsonParser.getClass.getClassLoader.getResource("sample.json").toURI))
    val json  = new String(bytes)
    println(parseAll(value, json))
  }
}
