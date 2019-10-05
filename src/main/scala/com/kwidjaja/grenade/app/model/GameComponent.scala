package com.kwidjaja.grenade.app.model

/**
  * Trait as the base type of components in the Game Actor.
  *
  * @author widjajk
  * @since 3/10/19 4:38 PM
  */
sealed trait GameComponent {
  def coordinate: Coordinate

  /**
    * Check if the first `c1` coordinate's radius covered the second `c2` coordinate.
    *
    * @param other Other coordinate to check against self.
    */
  def isInRadius(other: Coordinate): Boolean =
    ((coordinate.point.x + coordinate.radius.range) > (other.point.x + other.radius.range)) &&
      (coordinate.point.y + coordinate.radius.range) > (other.point.y + other.radius.range)
}

case class Grenade(coordinate: Coordinate) extends GameComponent
case class Player(coordinate: Coordinate) extends GameComponent
case class GridMap(coordinate: Coordinate) extends GameComponent
