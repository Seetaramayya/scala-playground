package com.seeta.reflection

import com.seeta.traits.Base

import scala.reflect.runtime.universe._

import scala.reflect.api.Trees

/** Scala reflection documentation : https://docs.scala-lang.org/overviews/reflection/symbols-trees-types.html
  * @author
  *   Seeta (Ramayya) Vadali
  */

class Sample[T] { def test[U](x: T)(y: U): Int = ??? }

object FirstExample extends App {
  println("Reflection ")

  val testMember = typeOf[Sample[Int]].member(TermName("test"))

  val baseType = typeOf[Base]

  println(s"test member('test') is implicit? ${testMember.isImplicit}")
  println(s"test member('test') name = ${testMember.name}")
  println(s"test member('test') is method? ${testMember.isMethod}")
  println(s"test member('test') is constructor? ${testMember.isConstructor}")
  println(s"test member('test') is package? ${testMember.isPackage}")
}
