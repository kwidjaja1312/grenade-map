package com.kwidjaja.grenade

import com.kwidjaja.grenade.app.model._
import com.typesafe.config.{Config, ConfigFactory}

import scala.language.postfixOps

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
    def defaultGrid: GridMap = (GridMap.apply _ compose initialCoordinateOf)("grid-map")

    /**
      * Retrieve the default [[Grenade]] based on the measurement from the config.
      */
    def grenade: Grenade = (Grenade.apply _ compose initialCoordinateOf)("grenade")

    /**
      * Retrieve the default [[Player]] based on the measurement from the config.
      */
    def player: Player = (Player.apply _ compose initialCoordinateOf)("player")

    private def initialCoordinateOf: String => Coordinate = { component =>
      val coordinateConfig: Config = gameConfig.getObject(component).toConfig

      assert(coordinateConfig.getInt("x") >= 0, "X Coordinate must be positive integer")
      assert(coordinateConfig.getInt("y") >= 0, "Y Coordinate must be positive integer")

      val point: Point = Point(coordinateConfig.getInt("x"), coordinateConfig.getInt("y"))
      val radius: Radius = Radius(coordinateConfig.getInt("radius"))

      Coordinate(point, radius)
    }
  }
}
