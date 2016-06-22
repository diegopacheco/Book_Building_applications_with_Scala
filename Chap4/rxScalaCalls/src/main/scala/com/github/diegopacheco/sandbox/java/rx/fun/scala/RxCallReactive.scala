package com.github.diegopacheco.sandbox.java.rx.fun.scala

import com.fasterxml.jackson.databind.DeserializationFeature
import com.mashape.unirest.http.ObjectMapper
import com.mashape.unirest.http.Unirest
import java.util.ArrayList
import rx.lang.scala.Observable
import rx.lang.scala.subjects.PublishSubject
import rx.lang.scala.ImplicitFunctionConversions
import rx.functions.Action1

class GitHubPojo(var url: String = "") {
  def this() = { this("") }
  def getUrl(): String = url
  def setUrl(u: String): Unit = url = u
  override def toString: String = "URL Object: " + url
}

class JacksonMapper[T] extends ObjectMapper {
  val jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper()
  jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  override def readValue[T](value: String, valueType: Class[T]): T = {
    return jacksonObjectMapper.readValue(value, valueType)
  }
  override def writeValue(value: Object): String = {
    return jacksonObjectMapper.writeValueAsString(value);
  }
}

object RxCallReactive extends App {

  Unirest.setObjectMapper(new JacksonMapper[Object])

  var subject = PublishSubject.apply[String]()
  subject
   .flatMap { x => Observable.just( x.toUpperCase()) }
   .take(4)
   .subscribe(x => println("RX Print: " + x))

  val result = Unirest.get("https://api.github.com/orgs/octokit/repos").asObject(classOf[Array[GitHubPojo]])
  result.getBody.foreach { x => subject.onNext(x.toString()) }

  Thread.sleep(3000L)

}