package com.kwidjaja.grenade.app.util

import com.kwidjaja.grenade.app.model.{Coordinate, Grenade, Point, Radius}
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

/**
  * Trait contains definition of JSON Format for registered types.
  *
  * @author widjajk
  * @since 5/10/19 4:00 PM
  */
trait JsonFormatUtil {

  implicit val pointFormat: RootJsonFormat[Point] = jsonFormat2(Point)
  implicit val radiusFormat: RootJsonFormat[Radius] = jsonFormat1(Radius)
  implicit val coordinateFormat: RootJsonFormat[Coordinate] = jsonFormat2(Coordinate)
  implicit val grenadeFormat: RootJsonFormat[Grenade] = jsonFormat1(Grenade)
}
