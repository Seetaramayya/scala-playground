package com.seeta.amazon

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

/** @author
  *   Seeta (Ramayya) Vadali
  */
object StairsChallenge {
  type Step = Int
  type Ways = Int

  def numberOfWaysWithState(goal: Int)(implicit steps: Set[Step]): Int = {
    val waysByPosition = ArrayBuffer(1)
    (1 to goal).foreach { currentStep =>
      val allowedSubSteps = steps.map(currentStep - _).filter(_ >= 0)
      waysByPosition += allowedSubSteps.toList.map(step => waysByPosition(step)).sum
    }
    waysByPosition.last
  }

  def numberOfWaysRecursive(goal: Int)(implicit steps: Set[Step]): Ways = {
    @tailrec
    def loop(waysByPosition: ArrayBuffer[Ways], position: Step): Ways = {
      if (position > goal) {
        waysByPosition.last
      } else {
        val allowedSubSteps = steps.map(position - _).filter(_ >= 0)
        val value           = allowedSubSteps.toList.map(waysByPosition(_)).sum
        loop(waysByPosition += value, position + 1)
      }
    }

    loop(ArrayBuffer(1), 1)
  }

  def numberOfWays(goal: Int)(implicit steps: Set[Step]): Ways = {
//    numberOfWaysRecursive(goal)
    numberOfWaysWithState(goal)
  }
}
