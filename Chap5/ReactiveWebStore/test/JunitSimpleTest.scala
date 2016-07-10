import org.scalatest.junit.AssertionsForJUnit
import scala.collection.mutable.ListBuffer
import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import services.BaseService
import services.ProductService
import org.scalatestplus.play._
import scala.collection.mutable
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(classOf[Suite])
@Suite.SuiteClasses(Array(classOf[JunitSimpleTest]))
class JunitSimpleSuiteTest

class JunitSimpleTest extends PlaySpec with AssertionsForJUnit {
  
  @Test def testBaseService() {
    val s = new ProductService
    val result = s.findAll()
    assertEquals(None, result)
    assertTrue( result != null)
    println("All good junit works fine with ScalaTest and Play")
  }
  
}