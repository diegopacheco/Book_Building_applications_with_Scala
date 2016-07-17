import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.OneBrowserPerSuite
import org.scalatestplus.play.HtmlUnitFactory
import org.scalatestplus.play.OneServerPerSuite
 
class ImageControllerTestSpec  extends PlaySpec with OneServerPerSuite with OneBrowserPerSuite with HtmlUnitFactory {
  
    "ImageController" should {
      "insert a new image should be ok" in {
            goTo(s"http://localhost:${port}/product/add")
            click on id("name")
            enter("Blue Ball")
            click on id("details")
            enter("Blue Ball is a Awesome and simple product")
            click on id("price")
            enter("17.55")
            submit()
            
            goTo(s"http://localhost:${port}/image/add")
            singleSel("productId").value = "1"
            click on id("url")
            enter("http://myimage.com/img.jpg")
            submit()
            
      }
      
      "details from the image 1 should be ok" in {
            goTo(s"http://localhost:${port}/image/details/1")
            textField("url").value mustBe "http://myimage.com/img.jpg"
      }
      
      "update image 1 should be ok" in {
            goTo(s"http://localhost:${port}/image/details/1")
            textField("url").value = "http://myimage.com/img2.jpg"
            submit()
            
            goTo(s"http://localhost:${port}/image/details/1")
            textField("url").value mustBe "http://myimage.com/img2.jpg"
      }
      
      "delete a image should be ok" in {
            goTo(s"http://localhost:${port}/image/add")    
            singleSel("productId").value = "1"
            click on id("url")
            enter("http://myimage.com/img.jpg")
            submit()
            
            goTo(s"http://localhost:${port}/image")
            click on id("btnDelete")
      }
      
  }
 
  
}