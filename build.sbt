import sbt.Keys.libraryDependencies

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.14"

lazy val root = (project in file("."))
  .settings(
    name := "Spotify",
    libraryDependencies ++= Seq(
      "org.scalaj" %% "scalaj-http" % "2.4.2",
      "com.typesafe.play" %% "play-json" % "2.9.2",
      "org.scalatest" %% "scalatest" % "3.0.5",
    )
  )
