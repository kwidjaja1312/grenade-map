package com.kwidjaja.grenade.app.util

import com.kwidjaja.grenade.app.model._

import scala.language.postfixOps
import scala.util.Random

/**
  * Object to provide coordinate-related functions.
  *
  * @author widjajk
  * @since 3/10/19 4:44 PM
  */
object GameBoardHelper {

  /**
    * Generate a random coordinate's point in a given `gridMap`. Refer to the Assumptions in the
    * README.md.
    *
    * @param gridMap Instance of [[com.kwidjaja.grenade.app.model.GridMap GridMap]]
    */
  def randomCoordinatePointInGrid(gridMap: GridMap): Point = {
    val newX: Int = gridMap.coordinate.point.x + gridMap.coordinate.radius.range
    val newY: Int = gridMap.coordinate.point.y + gridMap.coordinate.radius.range

    Point(Random.between(5, newX - 5), Random.between(5, newY - 5))
  }

  /**
    * Find the `grenade` at the valid location in the `gridMap` that blasted/killed the `player`.
    *
    * @param grenades List of [[com.kwidjaja.grenade.app.model.Grenade Grenade]]
    * @param gridMap Instance of [[com.kwidjaja.grenade.app.model.GridMap GridMap]]
    * @param player Instance of [[com.kwidjaja.grenade.app.model.Player Player]]
    */
  def grenadeBlastThePlayer(
    grenades: List[Grenade],
    gridMap: GridMap,
    player: Player): Either[PlayerStatus, List[Grenade]] = {

    val validGrenades = grenades.filter(g => gridMap.isInRadius(g))
    validGrenades.find(_.isInRadius(player)) match {
      case Some(_) => Left(PlayerKilled)
      case None => Right(validGrenades)
    }
  }

  /**
    * Check if the `p2` player spawned at the same location with `p1` player. Return the `p2` player
    * if not.
    *
    * @param p1 First player.
    * @param p2 Second player.
    */
  def playerSpawnedInTheSameCoordinate(p1: Player, p2: Player): Either[PlayerStatus, Player] =
    if (p1.isInRadius(p2)) Left(UnableToSpawnPlayer) else Right(p2)

  /**
    * Draw the grid map to visualize the state of player and grenades.
    *
    * @param gridMap Instance of [[com.kwidjaja.grenade.app.model.GridMap GridMap]]
    * @param player Instance of [[com.kwidjaja.grenade.app.model.Player Player]]
    * @param grenades List of [[com.kwidjaja.grenade.app.model.Grenade Grenade]]
    */
  def drawGridMap(gridMap: GridMap, player: Player, grenades: List[Grenade]): String = {

    /** Filter the cell and fill in the `X` mark on specific `GridCell` */
    def fillInGrenades: List[GridCell] => List[GridCell] = { cells =>
      cells map { cell =>
        grenades.find(_.isInRadius(cell)) match {
          case Some(_) => cell.copy(content = "X")
          case None => cell
        }
      }
    }

    /** Filter the cell and fill in the `P` mark on specific `GridCell` */
    def fillInPlayer: List[GridCell] => List[GridCell] = { filledCells =>
      filledCells map { cell =>
        val inTheSameCoordinatePoint: Boolean =
          cell.coordinate.point.x == player.coordinate.point.x &&
            cell.coordinate.point.y == player.coordinate.point.y

        if (inTheSameCoordinatePoint) cell.copy(content = "P") else cell
      }
    }

    def stringifyCells: List[GridCell] => String = _.map(_.content).mkString("|", "|", "|")

    val radius: Int = gridMap.coordinate.radius.range
    val grid: List[List[GridCell]] = List.tabulate(radius, radius)({ (x, y) =>
      val coordinate: Coordinate = Coordinate(Point(x + 1, y + 1))
      GridCell(coordinate)
    })

    grid map fillInGrenades map fillInPlayer map stringifyCells mkString "\n"
  }
}
