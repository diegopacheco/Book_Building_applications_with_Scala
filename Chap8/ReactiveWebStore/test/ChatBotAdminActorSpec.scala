
import org.scalatestplus.play.PlaySpec
import actors.ChatMessage
import actors.ChatUserActor
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.testkit.TestKit
import akka.testkit.TestProbe
import akka.actor.Props
import org.scalatestplus.play._
import scala.concurrent.duration.DurationInt
import actors.ChatBotAdminActor
import actors.Tick

class ChatBotAdminActorSpec extends PlaySpec  {
  
  class Actors extends TestKit(ActorSystem("test")) 
  
  "ChatBotAdminActor" should {
    
      "be able to create Bot Admin in the Chat Room and Tick" in new Actors {
        
        val probe1 = new TestProbe(system)
        
        val actorRef = TestActorRef[ChatBotAdminActor](Props(new ChatBotAdminActor(system)))
        val botActor = actorRef.underlyingActor
        assert(botActor.context != null)
  
        receiveOne(11 seconds)            
    }
  }
  
}