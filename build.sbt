import Dependencies.*
import sbt.Keys.libraryDependencies

ThisBuild / scalaVersion := "2.13.11"
ThisBuild / organization := "nl.codecraftr"
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision
ThisBuild / scalacOptions ++= Seq("-Wunused:imports")
ThisBuild / scalafixScalaBinaryVersion := "2.13"

lazy val root = project
  .enablePlugins(ScalafmtPlugin)
  .in(file("."))
  .settings(
    name := "tapir-breakable-toy",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      scalaTest,
      mockito,
      "com.softwaremill.sttp.tapir" %% "tapir-core" % "1.2.10",
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % "1.2.10",
      "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % "1.2.10",
      "org.http4s" %% "http4s-dsl" % "0.23.18",
      "org.http4s" %% "http4s-ember-server" % "0.23.18",
      "org.typelevel" %% "cats-core" % "2.9.0",
      "org.slf4j" % "slf4j-log4j12" % "2.0.7" % Runtime
    )
  )
