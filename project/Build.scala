import sbt._
import sbt.Keys._

object ProjectBuild extends Build {

  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "pesel",
      organization := "gtug.wroclaw",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.9.1",
      // add other settings here
	  
	  libraryDependencies += "joda-time" % "joda-time" % "1.6.2" withSources(),
	  libraryDependencies += "org.scala-tools.time" % "time_2.9.1" % "0.5",
	  libraryDependencies ++= Seq("org.specs2" %% "specs2" % "1.8.2" % "test" withSources())

    )
  )
}
