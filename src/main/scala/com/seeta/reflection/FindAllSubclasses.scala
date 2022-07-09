package com.seeta.reflection

import com.seeta.traits.C

import scala.reflect.runtime.universe

/** @author
  *   Seeta (Ramayya) Vadali
  */
object FindAllSubclasses extends App {
  val mirror   = universe.runtimeMirror(getClass.getClassLoader)
  val typeBase = universe.typeOf[C]

  val classSymbol        = typeBase.typeSymbol.asClass
  private val subClasses = classSymbol.knownDirectSubclasses.map(_.asClass)
  val subClassNames      = subClasses.map(_.fullName).mkString(",")
  println(classSymbol.baseClasses.map(_.asClass).map(_.fullName).mkString(","))

  println("-" * 50)
  println(s"Class name ${classSymbol.name}")
  println(s"Class full name ${classSymbol.fullName}")
  println(s"is class abstract? => ${classSymbol.isAbstract}")
  println(s"Known direct subclasses => $subClasses")
  println(s"Sub class names => $subClassNames")

  println(typeBase.decl(universe.termNames.PACKAGE).fullName)
  println(typeBase.decl(universe.termNames.EMPTY_PACKAGE_NAME).fullName)
  println(typeBase.decl(universe.termNames.ROOTPKG).fullName)
  println("-" * 50)
}
