import com.google.inject.AbstractModule
import java.time.Clock
import services.{ApplicationTimer}
import services.IProductService
import services.ProductService
import services.ReviewService
import services.IReviewService
import services.ImageService
import services.IImageService

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.

 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
class Module extends AbstractModule {

  override def configure() = {
    // Use the system clock as the default implementation of Clock
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
    // Ask Guice to create an instance of ApplicationTimer when the
    // application starts.
    bind(classOf[ApplicationTimer]).asEagerSingleton()
    
    bind(classOf[IProductService]).to(classOf[ProductService]).asEagerSingleton()
    bind(classOf[IReviewService]).to(classOf[ReviewService]).asEagerSingleton()
    bind(classOf[IImageService]).to(classOf[ImageService]).asEagerSingleton()
    
  }

}
