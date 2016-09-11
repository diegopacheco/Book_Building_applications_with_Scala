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
   
   def listAllImages = Action {
      val future  =  imageService.findAll()
      val images  =  Awaits.get(5,future)
      val json    =  ImagesJson.toJson(images)
      Ok(json)
   }
  
}