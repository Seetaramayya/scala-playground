package com.seeta.basic

import java.util.UUID
import scala.reflect.ClassTag
import scala.util.Try

sealed trait AnimalConverter[T] {
  def convert(t: Animal): T
}
sealed trait Animal {
  def name: String
  def id: UUID = UUID.nameUUIDFromBytes(name.getBytes)

  def as[T <: Animal: ClassTag]: Option[T] = this match {
    case t: T => Try(t).toOption
    case _    => None
  }
}

case class Dog(name: String)   extends Animal
case class Cat(name: String)   extends Animal
case class Horse(name: String) extends Animal

class AnimalRepo {
  private val allAnimals: Seq[Animal] = (1 to 10)
    .flatMap { i => Vector(Dog(s"dog$i"), Cat(s"cat$i"), Horse(s"horse$i")) }

  def fetchAllAnimals(): Seq[Animal] = allAnimals

  def fetchById(id: UUID): Option[Animal] = allAnimals.find(_.id == id)
}

class TypeConversions {}

object Main extends App {
  val repo          = new AnimalRepo
  private val catId = UUID.nameUUIDFromBytes("cat9".getBytes)
  println(s"Converting to correct type should work ${repo.fetchById(catId).flatMap(_.as[Cat])}")
  println(s"Converting to wrong type should NOT work ${repo.fetchById(catId).flatMap(_.as[Dog])}")
  println(s"Converting to wrong type should NOT work ${repo.fetchById(catId).flatMap(_.as[Horse])}")
}
