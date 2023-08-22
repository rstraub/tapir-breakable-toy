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
    name := "scala-kata-base",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      scalaTest,
      mockito
    )
  )
