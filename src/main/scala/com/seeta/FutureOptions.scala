package com.seeta

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn

case class FutureOptions[A](inner: Future[Option[A]]) {
  def map[B](f: A => B): FutureOptions[B] = {
    FutureOptions(inner.map(_.map(f(_))))
  }

  def flatMap[B](f: A => FutureOptions[B]): FutureOptions[B] = {
    FutureOptions {
      inner.flatMap {
        case Some(a) => f(a).inner
        case None    => Future.successful(None)
      }
    }
  }

  def value: Future[Option[A]] = this.inner
}

object FutureOptionsTest extends App {
  val fa = FutureOptions(Future(Some(Set(1, 2, 3))))
  val fb = FutureOptions(Future(Some(Set(2, 3, 4))))
  val result = for {
    a <- fa
    b <- fb
  } yield a zip b

  val futureResult: Future[Option[Set[(Int, Int)]]] = result.value

  val in = StdIn.readLine("Type Either int or string")
  val input: Either[String, Int] =
    try {
      Right(in.toInt)
    } catch {
      case ex: Exception => Left(in)
    }

  println(input match {
    case Right(x) => x + 1
    case Left(x)  => x
  })

}
