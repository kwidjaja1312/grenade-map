package com.kwidjaja.grenade.app.server

import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.kwidjaja.grenade.app.util.JsonFormatUtil
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import com.kwidjaja.grenade.app.model.Grenade

import scala.concurrent.{ExecutionContext, Future}

/**
  * Class to provide routes for Game Client.
  *
  * @author widjajk
  * @since 5/10/19 3:31 PM
  */
class HttpRoute private (gameStore: GameStore)(
  implicit val system: ActorSystem,
  val materializer: ActorMaterializer,
  val executionContext: ExecutionContext)
  extends JsonFormatUtil {

  val routes: Route = receiveGrenades

  private def receiveGrenades: Route = post {
    path("grenades") {
      entity(as[List[Grenade]]) { grenades =>
        grenades.foreach(println)
        complete("Print the GridMap")
      }
    }
  }
}

/** Companion object for [[HttpRoute]] */
object HttpRoute {

  /**
    * Create new instance of [[HttpRoute]].
    *
    * @param gameStore Instance of [[GameStore]] to store the game's states
    * @param system Implicit. Instance of [[ActorSystem]].
    * @param materializer Implicit. Instance of [[ActorMaterializer]].
    * @param executionContext Implicit. Instance of [[ExecutionContext]].
    */
  def apply(gameStore: GameStore)(
    implicit system: ActorSystem,
    materializer: ActorMaterializer,
    executionContext: ExecutionContext): HttpRoute = new HttpRoute(gameStore)
}
