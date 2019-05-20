package com.seeta.reflection

import com.google.common.reflect.ClassPath

import collection.JavaConverters._
import scala.reflect.runtime.universe
/**
  * Instantiate a type at Runtime:
  * https://docs.scala-lang.org/overviews/reflection/overview.html
  * @author Seeta (Ramayya) Vadali
  */
case class Person(name: String)
object CreateInstance extends App {
  private val loader = getClass.getClassLoader

  def createInstanceFromType: universe.MethodMirror = {
    //obtaining correct mirror
    val mirror = universe.runtimeMirror(loader)

    // obtain class symbol which can be used to reflect
    val classSymbol = universe.typeOf[Person].typeSymbol.asClass

    // obtain method symbol, in other words obtain handle to constructor
    val constructorAsMethod = classSymbol.selfType.decl(universe.termNames.CONSTRUCTOR).asMethod

    mirror.reflectClass(classSymbol).reflectConstructor(constructorAsMethod)
  }

  def createInstanceFromFullyQualifiedName(className: String): universe.MethodMirror = {
    val mirror = universe.runtimeMirror(loader)
    val classSymbol = mirror.staticClass(className)
    val construtorAsMethod = classSymbol.selfType.decl(universe.termNames.CONSTRUCTOR).asMethod
    mirror.reflectClass(classSymbol).reflectConstructor(construtorAsMethod)
  }

  def createInstanceFrom(classSymbol: universe.ClassSymbol): universe.MethodMirror = {
    val constructor = classSymbol.selfType.decl(universe.termNames.CONSTRUCTOR).asMethod
    universe.runtimeMirror(loader).reflectClass(classSymbol).reflectConstructor(constructor)
  }

  def getClassSymbol(className: String): universe.ClassSymbol = {
    universe.runtimeMirror(loader).staticClass(className)
  }

  private def findAllClassInPackage(packageName: String): Set[universe.ClassSymbol] = {
    ClassPath.from(loader).getTopLevelClasses(packageName).asScala.toList
      .map(info => universe.runtimeMirror(loader).staticClass(info.getName)).toSet
  }

  private val person1 = createInstanceFromType("seeta").asInstanceOf[Person]
  private val person2 = createInstanceFromFullyQualifiedName("com.seeta.reflection.Person")("seeta").asInstanceOf[Person]
  println(s"First instance of Person is $person1 and Second instance of Person is $person2")
  println(s"Reference equality is '${person1.eq(person2)}', but object equality '${person1 == person2}'")

  println(getClassSymbol("com.seeta.reflection.Person").isClass)
  val person = createInstanceFrom(getClassSymbol("com.seeta.reflection.Person"))("seeta").asInstanceOf[Person]
  println(person)

  findAllClassInPackage("com.seeta")
    .filter(classSymbol => !classSymbol.isAbstract).foreach(println)
}