package com.seeta.traits

import scala.collection.mutable.ArrayBuffer

abstract class IntQueue extends AnyRef {
  def put(x: Int): Unit
  def get(): Int
}

class BasicIntQueue extends IntQueue {
  private val buffer = new ArrayBuffer[Int]()
  override def put(x: Int): Unit = buffer += x
  override def get(): Int = buffer.remove(0)
}

trait Doubling extends IntQueue {
  abstract override def put(x: Int) = super.put(x * 2)
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int) = if (x >= 0) super.put(x)
}

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) = super.put(x + 1)
}

/**
  * Programing in Scala 12.5: Traits as stackable modifications.
  *
  * abstract override is only used in the case of traits.
  * trait further to the right calls the method first then before one etc...
  *
  * This is very flexible solution. With the above 3 traits 16 different classes
  * can be defined.
  *
  * 0 trait(s) -> 1 possible
  * 1 trait(s) -> 3 possible
  * 2 trait(s) -> 6 possible
  * 3 trait(s) -> 6 possible
  *
  * @author Seeta (Ramayya) Vadali
  */
object StackableModifications {
  val filterAndIncrementQueue = new BasicIntQueue with Incrementing with Filtering
  val incrementAndFilterQueue = new BasicIntQueue with Filtering with Incrementing
}
