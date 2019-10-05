package com.kwidjaja.grenade.app.model

import com.kwidjaja.grenade.app.AppConfig
import com.kwidjaja.grenade.app.util.GameBoardHelper
import org.scalatest.{FlatSpec, Matchers}

import scala.language.postfixOps
import scala.util.Random

/**
  *
  * @author widjajk
  * @since 5/10/19 3:48 PM
  */
class ComponentTest extends FlatSpec with Matchers {

  val initialGridMap: GridMap = AppConfig.defaultGrid
  val initialGrenade: Grenade = AppConfig.grenade
  val initialPlayer: Player = AppConfig.player

  "A Grenade" should "not collided with Player upon game init" in {
    initialGrenade isInRadius initialPlayer shouldBe false
  }

  it should "be in the Grid Map radius upon game init" in {
    initialGridMap isInRadius initialGrenade shouldBe true
  }

  it should "be in located in the GridMap upon new blast" in {
    val newRadius: Radius = Radius(Random.between(0, 5))
    val newPoint: Point = GameBoardHelper.randomCoordinatePointInGrid(initialGridMap)
    val newGrenade: Grenade = Grenade(Coordinate(newPoint, newRadius))

    initialGridMap isInRadius newGrenade shouldBe true
  }

  it should "be collided with the Player in the second attempt" in {
    val newCoordinate: Coordinate = Coordinate(Point(4, 4), Radius(10))
    val grenade: Grenade = initialGrenade.copy(newCoordinate)
    grenade.isInRadius(initialPlayer) shouldBe true
  }

  "A Player" should "be able to re-spawn given a new coordinate in a grid" in {
    val newPointInGrid: Point = GameBoardHelper.randomCoordinatePointInGrid(initialGridMap)
    val newCoordinate: Coordinate = Coordinate(newPointInGrid)
    val movedPlayer: Player = initialPlayer moveTo newCoordinate

    initialGridMap isInRadius movedPlayer shouldBe true
  }
}
