package dao

import scala.concurrent.Future
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile
import slick.jdbc.GetResult
import models.Review

class ReviewDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] with BaseDao[Review] {
  
  import driver.api._
  
  class ReviewTable(tag: Tag) extends Table[Review](tag, models.ReviewDef.toTable) {
    def id           = column[Option[Long]]("ID", O.PrimaryKey)
    def productId    = column[Option[Long]]("PRODUCT_ID")
    def author       = column[String]("AUTHOR")
    def comment      = column[String]("COMMENT")
    def * = (id, productId, author,comment) <> (Review.tupled, Review.unapply _)
  }
  
  override def toTable = TableQuery[ReviewTable]

  private val Reviews = toTable()
  
  override def findAll(): Future[Seq[Review]] = db.run(Reviews.result)
  
  override def findById(id:Long): Future[Option[Review]] = db.run(Reviews.filter( _.id === id).result.headOption)
  
  override def remove(id:Long): Future[Int] = db.run(Reviews.filter( _.id === id).delete)
  
  override def insert(r:Review): Future[Unit] = db.run(Reviews += r).map { _ => () }
  
  override def update(r2:Review) = Future[Unit] { 
    db.run(
       Reviews.filter(_.id === r2.id)
         .map(i => (i.productId,i.author, i.comment))
         .update((r2.productId,r2.author,r2.comment))
    )
  }
  
}