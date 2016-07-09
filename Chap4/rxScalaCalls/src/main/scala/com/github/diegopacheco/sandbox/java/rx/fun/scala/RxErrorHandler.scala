package com.github.diegopacheco.sandbox.java.rx.fun.scala

import rx.lang.scala.Observable

object RxErrorHandler extends App {
    
    val result = Observable.just("Request data...")  
                   .flatMap { x => println("Doing something dangerous... ") ; throw new RuntimeException("Error") }
                   .onErrorReturn { x => "Static Fallback" }
                   .toBlocking
                   .first
     println(result)
                   
    val result2 = Observable.just("Request data...")  
     .flatMap { x => 
             println("dangerous... ")
             try{
               throw new RuntimeException("Error")  
             } catch {
               case e:Exception => Observable.error(e)
             }
     }
     .retry(3)               
     .onErrorReturn { x => "Static Fallback 2" }
     .toBlocking
     .first
     println(result2)
  
}