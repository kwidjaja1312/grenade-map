package com.kwidjaja.grenade.app.server

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.kwidjaja.grenade.app.AppConfig
import com.kwidjaja.grenade.app.model._
import com.kwidjaja.grenade.app.util.GameBoardHelper._
import com.kwidjaja.grenade.app.util.JsonFormatUtil

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext
import scala.language.postfixOps

/**
  * Class to provide routes for Game Client.
  *
  * @author widjajk
  * @since 5/10/19 3:31 PM
  */
class HttpRoute(
  implicit val system: ActorSystem,
  val materializer: ActorMaterializer,
  val executionContext: ExecutionContext)
  extends JsonFormatUtil {

  val routes: Route = receiveGrenades

  // Buffer to hold the player after each request processed and server teleported it automatically
  private val playerContainer: ListBuffer[Player] = ListBuffer.empty
  private val gridMap: GridMap = AppConfig.defaultGrid

  private def receiveGrenades: Route = post {
    path("grenades") {
      entity(as[ClientRequest]) { request =>
        // Check if the player is exists. It will not be existed when the game is started.
        val currentPlayer: Option[Player] = playerContainer.headOption

        // The third pattern match below is to check if the new player's coordinate is the same with
        // the last player's coordinate
        val eitherPlayer: Either[PlayerStatus, Player] = (currentPlayer, request.player) match {
          case (Some(p1), None) => Right(p1)
          case (None, Some(p2)) => Right(p2)
          case (Some(p1), Some(p2)) => playerSpawnedInTheSameCoordinate(p1, p2)
          case (None, None) => Left(PlayerNotExists)
        }

        // Check if any of grenades blasted the player
        val eitherPlayerAndGrenades: Either[PlayerStatus, (Player, List[Grenade])] = for {
          player   <- eitherPlayer
          grenades <- grenadeBlastThePlayer(request.grenades, gridMap, player)
        } yield (player, grenades)

        eitherPlayerAndGrenades match {
          // Clear the container since the player got killed and marked as the end of the game
          case Left(pk @ PlayerKilled) =>
            // This is dangerous in the real world since there will be concurrency process updating
            // the list. But it should sufficient for the assignment purpose.
            playerContainer.clear()
            complete(StatusCodes.OK, pk.statusMsg)

          // Ignore the everything and respond to the Client immediately
          case Left(us @ UnableToSpawnPlayer) => complete(StatusCodes.OK, us.statusMsg)

          // Ignore the everything and respond to the Client immediately
          case Left(pn @ PlayerNotExists) => complete(StatusCodes.BadRequest, pn.statusMsg)

          // Valid request
          case Right(playerAndGrenadeTuple) =>
            val (player, grenades): (Player, List[Grenade]) = playerAndGrenadeTuple

            val newPlayerCoordinate: Coordinate = {
              val newPlayerCoordinatePoint: Point = randomCoordinatePointInGrid(gridMap)
              Coordinate(newPlayerCoordinatePoint, player.coordinate.radius)
            }
            // Teleport the player and store it to the buffer to prepare for the next grenade
            (player.moveTo _ andThen playerContainer.prepend)(newPlayerCoordinate)
            // Draw the state of the grid with player and grenades
            println(drawGridMap(gridMap, player, grenades) + "\n")
            complete(StatusCodes.OK)
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
    * @param system           Implicit. Instance of [[ActorSystem]].
    * @param materializer     Implicit. Instance of [[ActorMaterializer]].
    * @param executionContext Implicit. Instance of [[ExecutionContext]].
    */
  def apply()(
    implicit system: ActorSystem,
    materializer: ActorMaterializer,
    executionContext: ExecutionContext): HttpRoute = new HttpRoute()
}
