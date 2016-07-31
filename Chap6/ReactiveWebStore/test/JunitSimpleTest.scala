import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.scalatest.junit.AssertionsForJUnit
import org.scalatestplus.play.PlaySpec

import mocks.ProductDaoMocked
import services.ProductService

@RunWith(classOf[Suite])
@Suite.SuiteClasses(Array(classOf[JunitSimpleTest]))
class JunitSimpleSuiteTest

class JunitSimpleTest extends PlaySpec with AssertionsForJUnit {
   
  @Test def testBaseService() {
    val s = new ProductService(new ProductDaoMocked)
    val result = s.findAll()
    assertEquals(None, result)
    assertTrue( result != null)
    println("All good junit works fine with ScalaTest and Play")
  }
  
}