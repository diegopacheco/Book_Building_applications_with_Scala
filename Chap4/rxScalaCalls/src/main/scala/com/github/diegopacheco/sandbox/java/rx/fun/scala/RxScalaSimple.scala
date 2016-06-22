package com.github.diegopacheco.sandbox.java.rx.fun.scala

import rx.lang.scala.Observable
import scala.concurrent.duration._

object RxScalaSimple extends App {
    
    val o = Observable.interval(200 millis).take(5)
    o.subscribe(n => println("n = " + n))
    
    val secondOb:Observable[Long] = o.merge(Observable.just(5))
    val resultObservable = secondOb.reduce(_ + _)

    resultObservable.subscribe { n =>
      println(s"Result: $n!")
    }
    
    Thread.sleep(5000L)
}