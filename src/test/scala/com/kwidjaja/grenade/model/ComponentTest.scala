package com.kwidjaja.grenade.model

import akka.actor.CoordinatedShutdown
import com.kwidjaja.grenade.app.AppConfig
import com.kwidjaja.grenade.app.model.{Coordinate, Grenade, GridMap, Player, Point, Radius}
import org.scalatest.FlatSpec

/**
  *
  * @author widjajk
  * @since 5/10/19 3:48 PM
  */
class ComponentTest extends FlatSpec {
  val initialGridMap: GridMap = AppConfig.defaultGrid
  val initialGrenade: Grenade = AppConfig.grenade
  val initialPlayer: Player = AppConfig.player

  "A Grenade" should "not collided with Player upon game init" in {
    assert(!initialGrenade.isInRadius(initialPlayer))
  }

  it should "collided with the Player in the second attempt" in {
    val newCoordinate: Coordinate = Coordinate(Point(4, 4), Radius(10))
    val grenade: Grenade = initialGrenade.copy(newCoordinate)
    assert(grenade.isInRadius(initialPlayer))
  }
}
