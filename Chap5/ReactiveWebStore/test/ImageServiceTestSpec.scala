import org.scalatestplus.play._
import scala.collection.mutable
import services.IImageService
import services.ImageService

class ImageServiceTestSpec extends PlaySpec {
    
      "ImageService" must {
        
        val service:IImageService = new ImageService
      
        "insert a image properly" in {
           val image = new models.Image(Some(1),Some(1),"http://www.google.com.br/myimage")
           service.insert(image)
        }
        
        "update a image" in {
           val image = new models.Image(Some(2),Some(1),"http://www.google.com.br/myimage")
           service.update(1, image)
        }
        
        "not update because does not exist" in {
          intercept[RuntimeException]{
             service.update(333,null)
          }
        }
        
        "find the image" in {
           val image = service.findById(1)
           image.get.id mustBe Some(1)
           image.get.productId mustBe Some(1)
           image.get.url mustBe "http://www.google.com.br/myimage"
        }
        
        "find all" in {
          val reviews = service.findAll()
          reviews.get.length mustBe 1
          reviews.get(0).id mustBe Some(1)
          reviews.get(0).productId mustBe Some(1)
          reviews.get(0).url mustBe "http://www.google.com.br/myimage"
        }
        
        "remove 1 image" in {
          val image = service.remove(1)
          image mustBe true
          
          val oldImage = service.findById(1)
          oldImage mustBe None
        }
        
        "not remove because does not exist" in {
          intercept[RuntimeException]{
             service.remove(-1)
          }
        }
        
    }
  
}