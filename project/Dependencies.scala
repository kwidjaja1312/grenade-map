import sbt._

/**
  * Project Dependencies.
  *
  * @author widjajk
  * @since 3/10/19 3:15 PM
  */
object Dependencies {

  lazy val scalaTestVersion = "3.0.8"

  val dependencies: Seq[ModuleID] = Seq(
    "org.scalatest" % "scalatest_2.13" % scalaTestVersion % "test"
  )
}
