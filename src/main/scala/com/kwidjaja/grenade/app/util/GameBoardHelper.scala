package com.kwidjaja.grenade.app.util

import com.kwidjaja.grenade.app.model.{Grenade, GridMap, Player, PlayerKilled, PlayerStatus, Point, UnableToSpawnPlayer}

import scala.util.Random

/**
  * Object to provide coordinate-related functions.
  *
  * @author widjajk
  * @since 3/10/19 4:44 PM
  */
object GameBoardHelper {

  /**
    * Generate a random coordinate's point in a given `gridMap`.
    *
    * @param gridMap Instance of [[com.kwidjaja.grenade.app.model.GridMap GridMap]]
    */
  def randomCoordinatePointInGrid(gridMap: GridMap): Point = {
    val newX: Int = gridMap.coordinate.point.x + gridMap.coordinate.radius.range
    val newY: Int = gridMap.coordinate.point.y + gridMap.coordinate.radius.range

    Point(Random.between(0, newX), Random.between(0, newY))
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
    * @param player Instance of [[com.kwidjaja.grenade.app.model.Player Player]]
    * @param grenades List of [[com.kwidjaja.grenade.app.model.Grenade Grenade]]
    */
  def drawGridMap(player: Player, grenades: List[Grenade]): String = ???
}
