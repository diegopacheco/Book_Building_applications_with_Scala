package services

import java.util.concurrent.atomic.AtomicLong
import scala.collection.mutable.HashMap

trait BaseService[A] {
  
  var inMemoryDB = new HashMap[Long,A]
  var idCounter = new AtomicLong(0)
  
  def insert(a:A):Long
  def update(id:Long,a:A):Boolean
  def remove(id:Long):Boolean
  def findById(id:Long):Option[A]
  def findAll():Option[List[A]]
  
}
