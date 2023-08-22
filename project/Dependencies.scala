import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.16" % Test
  lazy val mockito = "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % Test
}
