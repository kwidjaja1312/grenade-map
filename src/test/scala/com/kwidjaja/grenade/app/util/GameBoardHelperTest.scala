package com.kwidjaja.grenade.app.util

import com.kwidjaja.grenade.app.model.{Coordinate, Grenade, Point, Radius}
import org.scalatest.{FlatSpec, Matchers}

/**
  *
  * @author widjajk
  * @since 7/10/19 12:14 PM
  */
class GameBoardHelperTest extends FlatSpec with Matchers {

  import com.kwidjaja.grenade.app.AppConfig._

  "A GameBoard" should "print out the top border line" in {
    val gridString: String = GameBoardHelper.drawGridMap(defaultGrid, player, List(grenade))
    println(gridString + "\n")
  }

  it should "printed multiple grenades" in {
    val point1: Point = GameBoardHelper.randomCoordinatePointInGrid(defaultGrid)
    val radius1: Radius = Radius(4)
    val grenade1: Grenade = grenade.copy(coordinate = Coordinate(point1, radius1))

    val gridString: String = GameBoardHelper.drawGridMap(defaultGrid, player, List(grenade, grenade1))
    println(gridString + "\n")
  }
}
