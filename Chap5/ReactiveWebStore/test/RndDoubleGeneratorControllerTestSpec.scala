import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.OneBrowserPerSuite
import org.scalatestplus.play.HtmlUnitFactory
import org.scalatestplus.play.OneServerPerSuite
import scala.concurrent.Await
import scala.concurrent.Future
import scala.concurrent.duration._
import play.api.libs.ws.WSClient
import javax.inject.Inject

class RndDoubleGeneratorControllerTestSpec @Inject() (ws:WSClient) extends PlaySpec with OneServerPerSuite with OneBrowserPerSuite with HtmlUnitFactory {
     
     import play.api.libs.concurrent.Execution.Implicits.defaultContext 
  
     "Assuming ng-microservice is down rx number should be" in {
        
        val future = ws.url(s"http://localhost:${port}/rnd/rxbat").get().map { res => res.body }
        val response = Await.result(future, 5.seconds)

        response mustBe "2.3000000000000007"
     }
  
}