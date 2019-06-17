import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContextExecutor

object ScalaAkkaApi extends App {

  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  case class Test(message: String)

  val route =
    path("test") {
      post {
        entity(as[String]) { test =>
          complete(test)
        }
      }
    }

  val port = sys.env("PORT").toInt
  val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", port)

  println(s"Server online at http://localhost:$port/\nPress RETURN to stop...")

}
