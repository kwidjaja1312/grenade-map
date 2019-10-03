package com.kwidjaja.grenade.app.model

/**
  * Trait as the base type of components in the Game Board.
  *
  * @author widjajk
  * @since 3/10/19 4:36 PM
  */
sealed trait GameBoard

case class Coordinate(point: Point, radius: Radius) extends GameBoard
case class Stage(point: Point, radius: Radius) extends GameBoard
