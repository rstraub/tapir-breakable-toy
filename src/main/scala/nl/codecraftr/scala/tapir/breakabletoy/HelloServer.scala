package nl.codecraftr.scala.tapir.breakabletoy

import cats.effect._
import com.comcast.ip4s.{Host, Port}
import org.http4s.HttpRoutes
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Router
import sttp.tapir._
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.http4s.Http4sServerInterpreter
import sttp.tapir.swagger.bundle.SwaggerInterpreter

import scala.concurrent.ExecutionContext

object HelloServer extends IOApp {

  private def countCharacters(s: String): IO[Either[Unit, Int]] =
    IO.pure(Right[Unit, Int](s.length))

  private val countCharactersEndpoint: PublicEndpoint[String, Unit, Int, Any] =
    endpoint.in(stringBody).out(plainBody[Int])
  private val countCharactersServerEndpoint: ServerEndpoint[Any, IO] =
    countCharactersEndpoint.serverLogic(countCharacters)

  private val docEndpoints: List[ServerEndpoint[Any, IO]] = SwaggerInterpreter()
    .fromEndpoints[IO](
      List(countCharactersEndpoint),
      "Hello API",
      "1.0.0"
    )

  implicit val ec: ExecutionContext =
    scala.concurrent.ExecutionContext.Implicits.global

  private val routes = Http4sServerInterpreter[IO]().toRoutes(
    docEndpoints :+ countCharactersServerEndpoint
  )

  override def run(args: List[String]): IO[ExitCode] = {
    EmberServerBuilder
      .default[IO]
      .withHost(Host.fromString("localhost").get)
      .withPort(Port.fromInt(8000).get)
      .withHttpApp(
        Router("/" -> routes).orNotFound
      )
      .build
      .use { server =>
        for {
          _ <- IO.println(
            s"Go to http://localhost:${server.address.getPort}/docs to open SwaggerUI. Press ENTER key to exit."
          )
          _ <- IO.readLine
        } yield ()
      }
      .as(ExitCode.Success)
  }
}
