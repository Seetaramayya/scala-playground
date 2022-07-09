package com.seeta

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class FutureTests {}

object FutureTests extends App {
  val f1 = Future.successful(1)
  val f2 = Future.successful(1)
  val f3 = Future.successful(1)

  val futuresSet: Set[Future[Int]] = Set(f1, f2, f3)
  val futureSet: Future[Set[Int]]  = Future.sequence(futuresSet)

  futureSet.onComplete {
    case Success(set) => set.mkString(",")
    case Failure(ex)  => ex.printStackTrace()
  }
}
