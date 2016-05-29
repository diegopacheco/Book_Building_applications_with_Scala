package controllers


import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data._
import play.api.i18n.Messages
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi
import services.IProductService
import play.Application
import services.IReviewService
import models.Review

@Singleton
class ReviewController @Inject() 
  (val messagesApi:MessagesApi,
   val productService:IProductService,
   val service:IReviewService) 
extends Controller with I18nSupport {
  
    val reviewForm:Form[Review] = Form(
    mapping(
      "id"        -> optional(longNumber),  
      "productId" -> optional(longNumber),
      "author"    -> text,
      "comment"   -> text
    )(models.Review.apply)(models.Review.unapply))

  def index = Action { implicit request =>
    val reviews = service.findAll().getOrElse(Seq(Review(Some(0),Some(0),"","")))
    Logger.info("index called. Reviews: " + reviews)
    Ok(views.html.review_index(reviews))
  }

  def blank = Action { implicit request =>
    Logger.info("blank called. ")
    Ok(views.html.review_details(None, reviewForm))
  }

  def details(id: Long) = Action { implicit request =>
    Logger.info("details called. id: " + id)
    val review = service.findById(id).get
    Ok(views.html.review_details(Some(id), reviewForm.fill(review)))
  }

  def insert()= Action { implicit request =>
    Logger.info("insert called.")
    reviewForm.bindFromRequest.fold(
      form => {
        BadRequest(views.html.review_details(None, form))
      },
      product => {
        val id = service.insert(product)
        Redirect(routes.ReviewController.index).flashing("success" -> Messages("success.insert", id))
      })
  }

  def update(id: Long) = Action { implicit request =>
    Logger.info("updated called. id: " + id)
    reviewForm.bindFromRequest.fold(
      form => {
        Ok(views.html.review_details(Some(id), form)).flashing("error" -> "Fix the errors!")
      },
      review => {
        service.update(id,review)
        Redirect(routes.ReviewController.index).flashing("success" -> Messages("success.update", review.productId))
      })
  }

  def remove(id: Long)= Action {
      service.findById(id).map { review =>
        service.remove(id)
        Redirect(routes.ReviewController.index).flashing("success" -> Messages("success.delete", review.productId))
      }.getOrElse(NotFound)
  }
 
  
}