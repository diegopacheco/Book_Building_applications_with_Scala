package actors

import akka.actor._

object Message

class PrinterActor extends Actor {
    
  def receive = {
     case a:Any => 
         println("Print: " + a)
  }
  
}

object FireAndForgetActorMainApp extends App{
    
    val system = ActorSystem("SimpleActorSystem")
    val actor  = system.actorOf(Props[PrinterActor])
    
    import scala.concurrent.duration._
    import akka.util.Timeout
    import akka.pattern.ask
    import scala.concurrent.Await
    implicit val timeout = Timeout(20 seconds) 
    
    val voidReturn = actor ! Message
    println("Actor says: " + voidReturn )
    
    system.terminate()
    
}