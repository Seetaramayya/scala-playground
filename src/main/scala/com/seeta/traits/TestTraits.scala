package com.seeta.traits

// Base -> AnyRef -> Any
class Base

// A -> Base
trait A extends Base {
  def a() = {
    println("A -> ")
  }
}

// B1 -> A
trait B1 extends A {
  abstract override def a() = {
    println("B1 -> ")
    super.a()
  }
}

trait B2 extends A {
  abstract override def a() = {
    println("B2 -> ")
    super.a()
  }
}

trait B3 extends B2 {
  abstract override def a() = {
    println("B3 -> ")
    super.a()
  }
}

// C ->
class C extends Base with B1 with B2 with B3 {
  override def a(): Unit = {
    println("C -> ")
    super.a()
  }
}

//
object TestTraits {
  def main(args: Array[String]): Unit = {
    val c = new C
    c.a()
  }
}
