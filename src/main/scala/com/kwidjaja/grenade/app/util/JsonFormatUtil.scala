package com.kwidjaja.grenade.app.util

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.kwidjaja.grenade.app.model.{Coordinate, Grenade, Player, Point, Radius}
import com.kwidjaja.grenade.app.server.ClientRequest
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

/**
  * Trait contains definition of JSON Format for registered types.
  *
  * @author widjajk
  * @since 5/10/19 4:00 PM
  */
trait JsonFormatUtil extends SprayJsonSupport with DefaultJsonProtocol {

  implicit protected val pointFormat: RootJsonFormat[Point] = jsonFormat2(Point)
  implicit protected val radiusFormat: RootJsonFormat[Radius] = jsonFormat1(Radius)
  implicit protected val coordinateFormat: RootJsonFormat[Coordinate] = jsonFormat2(Coordinate)
  implicit protected val grenadeFormat: RootJsonFormat[Grenade] = jsonFormat1(Grenade)
  implicit protected val playerFormat: RootJsonFormat[Player] = jsonFormat1(Player)
  implicit protected val clientRequestFormat: RootJsonFormat[ClientRequest] = jsonFormat2(ClientRequest)
}
