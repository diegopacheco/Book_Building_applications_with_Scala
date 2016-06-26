package services

import javax.inject._
import models.Image
import scala.collection.mutable.HashMap
import java.util.concurrent.atomic.AtomicLong

trait IImageService extends BaseService[Image]{
  def insert(image:Image):Long
  def update(id:Long,image:Image):Boolean
  def remove(id:Long):Boolean
  def findById(id:Long):Option[Image]
  def findAll():Option[List[Image]]
}

@Singleton
class ImageService extends IImageService{
    
  def insert(image:Image):Long = {
     val id = idCounter.incrementAndGet();
     image.id = Some(id)
     inMemoryDB.put(id, image)
     id
  }
  
  def update(id:Long,image:Image):Boolean = {
     validateId(id)
     image.id = Some(id)
     inMemoryDB.put(id, image)
     true  
  }
  
  def remove(id:Long):Boolean = {
     validateId(id)
     inMemoryDB.remove(id)
     true
  }
  
  def findById(id:Long):Option[Image] = {
     inMemoryDB.get(id)
  }
  
  def findAll():Option[List[Image]] = {
     if (inMemoryDB.values.toList == null || inMemoryDB.values.toList.size==0) return None
     Some(inMemoryDB.values.toList)
  }
  
  private def validateId(id:Long):Unit = {
     val entry = inMemoryDB.get(id)
     if (entry==null) throw new RuntimeException("Could not find Image: " + id)
  }
  
}