package com.seeta.reflection

import scala.reflect.runtime.universe

/** Instantiate a type at Runtime: https://docs.scala-lang.org/overviews/reflection/overview.html
  * @author
  *   Seeta (Ramayya) Vadali
  */
case class Person(name: String)
object CreateInstance extends App {
  // obtaining correct mirror
  val mirror = universe.runtimeMirror(getClass.getClassLoader)

  private val personType = universe.typeOf[Person]
  // obtain class symbol which can be used to reflect
  val classPerson = personType.typeSymbol.asClass

  // obtain class mirror
  val classMirror = mirror.reflectClass(classPerson)

  // obtain method symbol, in other words obtain handle to constructor
  val constructorAsMethod = personType.decl(universe.termNames.CONSTRUCTOR).asMethod

  val constructor = classMirror.reflectConstructor(constructorAsMethod)

  val seeta = constructor("Seeta")

  println(seeta)
}
