package cryptobees

import cats.effect._
import cats.implicits._
import org.http4s.{HttpRoutes, MediaType, StaticFile}
import org.http4s.dsl.io._
import org.http4s.headers.`Content-Type`
import org.http4s.implicits._
import org.http4s.server.blaze._
import org.http4s.server.staticcontent._
import scalatags.Text.all.{script, _}

import scala.concurrent.ExecutionContext.global

object CryptobeeServer extends IOApp {

  import java.util.concurrent._

  private val blockingPool = Executors.newFixedThreadPool(4)
  private val blocker = Blocker.liftExecutorService(blockingPool)

  val helloWorldService: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "hello" / name =>
      Ok(s"Hello my dude, $name.")
    case req @ GET -> Root / "assets" / path =>
      println(path)
      StaticFile.fromResource("/public/" + path, blocker, Some(req)).getOrElseF(NotFound())
    case GET -> Root =>
      Ok(index).map(_.withContentType(`Content-Type`(MediaType.text.html)))

  }

  val index = "<!DOCTYPE html>" + html(
    head(

    ),
    body(
      h1("This is my title"),
      div(
        p(onclick:="sayHello()")(
          "This is my first paragraph"
        ),
        a(href:="www.google.com")(
          p("Goooogle")
        ),
        p(hidden)(
          "I am hidden"
        )
      ),
      script(src := "/assets/client-opt-bundle.js")
    )
  )

  val httpApp = (helloWorldService).orNotFound

  def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO](global)
      .bindHttp(8080, "localhost")
      .withHttpApp(httpApp)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
}

