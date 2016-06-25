package com.github.diegopacheco.sandbox.java.rx.fun.scala

import rx.lang.scala.Observable
import rx.lang.scala.subjects.PublishSubject
import rx.lang.scala.ImplicitFunctionConversions
import scala.util.Random
import scala.util.Random.nextDouble
import scala.concurrent.Future
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.collection.Iterable
import java.util.concurrent.CountDownLatch

object RxKindComplexComposition extends App {
    
    var doubleInfiniteStreamSubject = PublishSubject.apply[Double]()
    Future { Stream.continually(nextDouble * 1000.0 ).foreach { x => doubleInfiniteStreamSubject.onNext(x) } }
    
    var observableEven = Observable.create { doubleInfiniteStreamSubject.subscribe }
      .flatMap { x => Observable.from( Iterable.fill(1)(x + 1000) ) }
      .filter { x => x.toInt % 2 == 0 }
      .flatMap { x => Observable.just("Observable Even: " + x) }
      
    var observableOdd = Observable.create { doubleInfiniteStreamSubject.subscribe }
      .flatMap { x => Observable.from( Iterable.fill(1)(x + 1000) ) }
      .filter { x => x.toInt % 2 != 0 }
      .flatMap { x => Observable.just("Observable Odd: " + x) }
    
    val cd = new CountDownLatch(10)
    var mergeObservable = Observable
       .empty
       .merge(observableEven)
       .merge(observableOdd)
       .take(10)
       .foreach { x => println("Observable Merge: " + x) ; cd.countDown() }
    
    cd.await()
}