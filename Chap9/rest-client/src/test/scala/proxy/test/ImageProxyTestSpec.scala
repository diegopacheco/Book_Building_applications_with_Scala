package proxy.test

import org.scalatest._
import proxy.ImageProxy

class ImageProxyTestSpec extends FlatSpec with Matchers {

  "A Image REST Proxy " should "return all images" in {
      val images = ImageProxy.listAll().get
      images shouldNot(be(null))
      images shouldNot(be(empty))
      
      for( i <- images){
         println(i)
      }
  }

}