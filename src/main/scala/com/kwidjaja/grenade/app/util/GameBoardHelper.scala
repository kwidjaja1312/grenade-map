package com.kwidjaja.grenade.app.util

import com.kwidjaja.grenade.app.model.{Grenade, GridMap, Player, Point}

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
  def grenadeKilledPlayer(grenades: List[Grenade], gridMap: GridMap, player: Player): Option[Grenade] =
    grenades.filter(g => gridMap.isInRadius(g)).find(_.isInRadius(player))
}
