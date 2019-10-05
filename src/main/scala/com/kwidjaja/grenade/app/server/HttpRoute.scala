package com.kwidjaja.grenade.app.server

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.kwidjaja.grenade.app.AppConfig
import com.kwidjaja.grenade.app.model.{GameStore, Grenade, GridMap, Player}
import com.kwidjaja.grenade.app.util.{GameBoardHelper, JsonFormatUtil}

import scala.concurrent.ExecutionContext

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
  private val gridMap: GridMap = AppConfig.defaultGrid

  private def receiveGrenades: Route = post {
    path("grenades") {
      entity(as[ClientRequest]) { request =>
        val currentPlayer: Player = gameStore.players.head

        if (request.player.exists(currentPlayer.isInRadius(_))) {
          complete(StatusCodes.BadRequest, "Unable to spawn Person in that location")
        } else {
          GameBoardHelper.grenadeKilledPlayer(request.grenades, gridMap, currentPlayer) match {
            case Some(_) =>
              complete(StatusCodes.OK, "Person killed")

            case None =>
              // Draw the Grid
              request.grenades.foreach(println)
              complete(StatusCodes.OK)
          }
        }
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
