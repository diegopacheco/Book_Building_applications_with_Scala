package com.github.diegopacheco.sandbox.java.rx.fun.scala

import rx.lang.scala.Observable
import scala.concurrent.duration._
import scala.concurrent.Await
import scala.concurrent.Future
import scala.concurrent._
import ExecutionContext.Implicits.global
import java.util.concurrent.CountDownLatch

object RxVerySimple extends App {
   
    // Await Sample
    val intervals: Observable[Long] = Observable.interval(100 millis).take(10)
    intervals subscribe {
      v => println(s"value = $v")
    }
    Await.result(Future { Thread.sleep(1500) }, 2 seconds)
    println("1 - DONE")
    
    // Ultra simple
    Observable.just(5, 4, 2).subscribe(println(_))
    println("2 - DONE")
    
    // Async Calls
    val asyncEmulation = Observable
    .just(1, 2, 3)
    .map(e => e + 1)
    .flatMap(e => Observable.from(Future { Thread.sleep(400 - 100 * e); e }))

    val cd = new CountDownLatch(3)
    asyncEmulation subscribe {
      v => println(s"received = $v"); cd.countDown()
    }
    cd.await()
    println("3 - DONE")
}