package com.kwidjaja.grenade.app

/** Package to contains types as models */
package object model {

  /**
    * Trait to define types of measurement in the game
    *
    * @author widjajk
    * @since 3/10/19 7:10 PM
    */
  sealed trait MeasurementUnit

  case class Point(x: Int, y: Int) extends MeasurementUnit

  case class Radius(range: Int = 0) extends MeasurementUnit

  case class Coordinate(point: Point, radius: Radius = Radius())
}
