package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import services.IProductService
import models.ProductsJson
import models.ReviewsJson
import models.ImagesJson
import utils.Awaits
import services.IReviewService
import services.IImageService
import backpresurre.LeakyBucket

@Singleton
class RestAPIController @Inject() 
   (val productService:IProductService,
    val reviewService:IReviewService,
    val imageService:IImageService) extends Controller {
   
   import play.api.libs.concurrent.Execution.Implicits.defaultContext
      
   def listAllProducts = Action {
      val future = productService.findAll()
      val products =  Awaits.get(5,future)
      val json =  ProductsJson.toJson(products)
      Ok(json)
   }
   
   def listAllReviews = Action {
      val future  = reviewService.findAll()
      val reviews =  Awaits.get(5,future)
      val json =  ReviewsJson.toJson(reviews)
      Ok(json)
   }
   
   import scala.concurrent.duration._
   var bucket = new LeakyBucket(5, 60 seconds)
   
   def processImages = {
       val future  =  imageService.findAll()
       val images  =  Awaits.get(5,future)
       val json    =  ImagesJson.toJson(images)
       json
   }
     
   def processFailure = {
     Json.toJson("Too Many Requests - Try Again later... ")
   }  
   
   def listAllImages = Action {
     bucket.dropToBucket() match {
       case true  => Ok(processImages) 
       case false => InternalServerError(processFailure.toString())
     }
   }
  
}