import sbt._

/**
  * Project Dependencies.
  *
  * @author widjajk
  * @since 3/10/19 3:15 PM
  */
object Dependencies {

  lazy val scalaTestVersion = "3.0.8"
  lazy val typesafeConfig = "1.3.4"
  lazy val akkaHttp = "10.1.10"
  lazy val akka = "2.5.25"

  val dependencies: Seq[ModuleID] = Seq(
    "com.typesafe.akka" %% "akka-http"            % akkaHttp,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttp,
    "com.typesafe.akka" %% "akka-stream"          % akka,
    "org.scalatest" %% "scalatest"                % scalaTestVersion % "test"
  ) ++ Seq(
    "com.typesafe" % "config" % typesafeConfig
  )
}
