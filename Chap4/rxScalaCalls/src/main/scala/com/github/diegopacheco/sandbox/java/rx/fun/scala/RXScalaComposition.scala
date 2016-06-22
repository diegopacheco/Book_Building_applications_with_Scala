package com.github.diegopacheco.sandbox.java.rx.fun.scala

import rx.lang.scala.Observable

object RXScalaComposition extends App {
    
    val first = Observable.just(10, 11, 12,13,15,18,44)
    val second = Observable.just(10, 11, 12,13,15,18,4)
    val booleans = for ((n1, n2) <- (first zip second)) yield (n1 == n2)
    
    booleans.subscribe { n =>
      println(s"Result: $n!")
    }
    
}