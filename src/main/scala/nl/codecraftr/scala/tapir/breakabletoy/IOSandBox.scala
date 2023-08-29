package nl.codecraftr.scala.tapir.breakabletoy

import cats.effect.{ExitCode, IO, IOApp}

object IOSandBox extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    for {
      age <- IO.pure(28)
      _ <- IO(println(s"aged $age"))
    } yield ExitCode.Success
  }
}
