package com.seeta.cats.chapter1

import cats.instances.int._
import cats.instances.list._
import cats.instances.map._
import cats.instances.set._
import cats.syntax.show._

object CatsShow extends App {
  println(2.show)
  println(List(1, 2, 3).show)
  println(Set(1, 2, 3).show)
  println(Map(1 -> 1, 2 -> 2).show)

  println(2)
  println(List(1, 2, 3))
  println(Set(1, 2, 3))
  println(Map(1 -> 1, 2 -> 2))
}
