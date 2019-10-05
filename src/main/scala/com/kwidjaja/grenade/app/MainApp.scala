package com.kwidjaja.grenade.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.kwidjaja.grenade.app.server.{GameStore, HttpRoute}

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

  def initServer(): Unit = {
    val gameStore: GameStore = GameStore(AppConfig.grenade, AppConfig.player)
    val serverRoutes: HttpRoute = HttpRoute(gameStore)

    val bindingFuture = Http().bindAndHandle(serverRoutes.routes, "localhost", 8080)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
  }
}
