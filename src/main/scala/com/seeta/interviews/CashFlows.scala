package com.seeta.interviews

import java.time.LocalDate
import java.util.UUID

/** We provide two CSV of cash flows (date, amount, clientId) one coming from a bank and one coming from our internal
  * system. We want to design an algorithm that looks for for mismatches.
  */

case class Record(date: LocalDate, amount: BigDecimal, clientId: UUID)

object CashFlows extends App {
  print("test")

  private def findDiff(
    bankRecords: Seq[Record],
    internalRecords: Seq[Record]
  ): Seq[Record] = {

    bankRecords.foldLeft()


    ???
  }

  val bankRecords    = (1 to 3).map(i => Record(LocalDate.now().minusDays(i), BigDecimal(i), UUID.randomUUID()))
  val internalRecord = (1 to 3).map(i => Record(LocalDate.now().minusDays(i), BigDecimal(i), UUID.randomUUID()))

  val result = findDiff(bankRecords, internalRecord)

  println(result)

}
