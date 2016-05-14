package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton 
class SimpleController @Inject() extends Controller {

    def index = Action { Ok("It works!") }
    
    def index2 = Action { 
       request => Ok("Got request [" + request + "]") 
    }
    
    def index3(name:String) = Action { Ok("It works! " + name) }
    
    def index4 = Action {
       Redirect("http://www.google.com.br")
    }

}
