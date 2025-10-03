package com.seeta.java.experiments

object VirtualThreads extends App {
  val platFormThread = Thread
    .ofPlatform()
    .unstarted(new Runnable() {
      override def run(): Unit = println(Thread.currentThread().getName)
    })

  platFormThread.start()
  platFormThread.join()
}
