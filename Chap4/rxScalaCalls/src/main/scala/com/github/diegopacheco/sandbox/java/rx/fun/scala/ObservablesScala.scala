package com.github.diegopacheco.sandbox.java.rx.fun.scala

import rx.lang.scala.Observable
import scala.util.Random

object ObservablesScala extends App {
  
    val o  = Observable.just( 1 to 10 )
    val ob = o.subscribe { x => println(x) }
    
    var r = new scala.util.Random
    val oInts =  Observable.from( 1 to 10 map r.nextInt )
    oInts.subscribe { x => println("oInts: " + x) }
        
    val obLongs = Observable.from(  Seq.fill(5)(Random.nextInt.toLong) )
    obLongs.subscribe { x => println("obLongs: " + x) }
    
    import util.Random.nextDouble
    val oDoubles = Observable.from( Stream.continually(nextDouble).take(10) )
    oDoubles.subscribe { x => println("oDoubles: " + x) }
    
}