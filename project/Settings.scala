import sbt._
import sbt.Keys._

import Aliases._

object Settings {

  private def scala211 = "2.11.12"
  private def scala212 = "2.12.10"
  private def scala213 = "2.13.1"

  lazy val shared = Seq(
    scalaVersion := scala212,
    crossScalaVersions := Seq(scala213, scala212, scala211),
    scalacOptions ++= {
      scalaBinaryVersion.value match {
        case "2.11" =>
          Seq("-target:jvm-1.7")
        case _ =>
          Nil
      }
    }
  )

  lazy val dontPublish = Seq(
    publish := {},
    publishLocal := {},
    publishArtifact := false
  )

  lazy val utest = Seq(
    libs += Deps.utest.value % "test",
    testFrameworks += new TestFramework("utest.runner.Framework")
  )

}
