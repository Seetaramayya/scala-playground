package com.seeta.future

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util._

object HelloFuture extends App {
  val f: Future[Int] = Future {
    val source = scala.io.Source.fromFile("src/main/resources/myText.txt")

    source.toSeq.indexOfSlice("myKeyword")
  }

  f.onComplete {
    case Success(idx) =>
      println(s"The keyword first appearces at position is $idx")
    case Failure(t) =>
      println("Future is failed")
      t.printStackTrace
  }

  println("Waiting for future to complete")
}
