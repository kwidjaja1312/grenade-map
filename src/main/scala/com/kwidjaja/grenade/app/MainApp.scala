package com.kwidjaja.grenade.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.kwidjaja.grenade.app.server.HttpRoute

import scala.concurrent.ExecutionContext
import scala.io.StdIn

/**
  * Application Entry-point.
  *
  * @author widjajk
  * @since 3/10/19 3:22 PM
  */
object MainApp extends App {

  implicit val system: ActorSystem = ActorSystem("grenade-map")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  /**
    * Use the command argument to specify the application role. This function will run the application
    * by boot the HTTP Route up to serve client's requests.
    */
  def initServer(): Unit = {
    val serverRoutes: HttpRoute = HttpRoute()

    val bindingFuture = Http().bindAndHandle(serverRoutes.routes, "0.0.0.0", 8080)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
  }

  initServer()
}
