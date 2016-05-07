import org.junit.Test
import org.junit.Assert._

class CalcTest {

	@Test
	def testSumOK():Unit = {
		val c:Calculator = new Calculator()
		val result:Int = c.sum(1,5)
		assertNotNull(c)
		assertEquals(6,result)
	}

	@Test
	def testSum0():Unit = {
		val c:Calculator = new Calculator()
		val result:Int = c.sum(0,0)
		assertNotNull(c)
		assertEquals(0,result)
	}

	@Test
	def testMultiplyOk():Unit = {
		val c:Calculator = new Calculator()
		val result:Int = c.multiply(2,3)
		assertNotNull(c)
		assertEquals(6,result)
	}

	@Test
	def testMultiply0():Unit = {
		val c:Calculator = new Calculator()
		val result:Int = c.multiply(5,0)
		assertNotNull(c)
		assertEquals(0,result)
	}

}