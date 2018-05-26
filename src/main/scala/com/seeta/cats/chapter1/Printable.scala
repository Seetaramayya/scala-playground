package com.seeta.cats.chapter1

import cats.Show

// Data
final case class Person(name: String, age: Int, color: String)

// Type class
trait Printable[A] {
  def format(value: A): String
}

// Type class instances
object PrintableInstances {
  implicit val stringPrintable: Printable[String] = (value: String) => value
  implicit val intPrintable: Printable[Int] = (value: Int) => s"Int = $value"

  def personToString(person: Person): String = s"${person.name} is a ${person.age} year old ${person.color} person."
  //User defined data type
  implicit val catPrintable: Printable[Person] = personToString
  implicit val personShowIntance: Show[Person] = Show.show(personToString)
}

// Type class interface
object Printable {
  def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)
  def print[A](value: A)(implicit printable: Printable[A]): Unit = println(format(value))
}
// or

// Type class interface syntax (in other words extension methods)
object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def print(implicit p: Printable[A]): Unit = println(format)
    def format(implicit p: Printable[A]): String = p.format(value)
  }
}

object PrintableDemo extends App {
  val person = Person("Blah", 22, "Black")
  import PrintableInstances.intPrintable
  println(Printable.format(3))

  //User defined data type
  import PrintableInstances.catPrintable
  Printable.print(person)

  import PrintableSyntax._
  person.print
  println(person.format)

  import cats.syntax.show._
  import PrintableInstances.personShowIntance
  println(person.show)
}
