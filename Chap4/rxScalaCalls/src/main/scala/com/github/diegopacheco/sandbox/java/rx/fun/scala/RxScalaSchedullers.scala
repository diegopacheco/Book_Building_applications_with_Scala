package com.github.diegopacheco.sandbox.java.rx.fun.scala

import rx.lang.scala.Observable
import rx.lang.scala.subjects.PublishSubject
import rx.lang.scala.schedulers.NewThreadScheduler
import rx.lang.scala.ImplicitFunctionConversions

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.Future

object RxScalaSchedullers extends App {
    
    var doubleSubject = PublishSubject.apply[Double]()
    
    Future {
      Thread.sleep(1000L)
       doubleSubject.onNext(10)
       doubleSubject.onNext(20)
       doubleSubject.onNext(30)
       doubleSubject.onCompleted()  
    }
    
    val rxResult = Observable.create { doubleSubject.subscribe }
          .subscribeOn(NewThreadScheduler())
          .take(3)
          .flatMap { x => println(x) ; Observable.just(x) }
          .foldLeft(0.0)(_+_)
          .toBlocking
          .first

    println("Result: " + rxResult)
          
}