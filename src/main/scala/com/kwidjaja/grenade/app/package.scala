package com.kwidjaja.grenade

import com.kwidjaja.grenade.app.model.{Coordinate, Grenade, GridMap, Player, Point, Radius}
import com.typesafe.config.{Config, ConfigFactory}

/** package to contains shareable objects */
package object app {

  /**
    * Application config.
    *
    * @author widjajk
    * @since 5/10/19 2:20 PM
    */
  final object AppConfig {
    private[this] val config = ConfigFactory.load()
    private[this] val gameConfig: Config = config.getConfig("app.game")

    /**
      * Retrieve the default [[GridMap]] based on the measurement from the config.
      */
    def defaultGrid: GridMap = (initialCoordinateOf andThen GridMap.apply)("grid-map")

    /**
      * Retrieve the default [[Grenade]] based on the measurement from the config.
      */
    def grenade: Grenade = (initialCoordinateOf andThen Grenade.apply)("grenade")

    /**
      * Retrieve the default [[Player]] based on the measurement from the config.
      */
    def player: Player = (initialCoordinateOf andThen Player.apply)("player")

    private def initialCoordinateOf: String => Coordinate = { component =>
      val coordinateConfig: Config = gameConfig.getObject(component).toConfig
      val point: Point = Point(coordinateConfig.getInt("x"), coordinateConfig.getInt("y"))
      val radius: Radius = Radius(coordinateConfig.getInt("radius"))
      Coordinate(point, radius)
    }
  }
}
