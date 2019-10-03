package com.kwidjaja.grenade.app.model

/**
  * Trait as the base type of components in the Game Actor.
  *
  * @author widjajk
  * @since 3/10/19 4:38 PM
  */
sealed trait GamePlayer {
  def coordinate: Coordinate

  /**
    * Check if the first {{c1}} coordinate's radius covered the second {{c2}} coordinate.
    *
    * @param other Other coordinate to check against self.
    */
  def overlapCoordinate(other: Coordinate): Boolean =
    coordinate.point.x + coordinate.radius.width > other.point.x &&
      coordinate.point.y + coordinate.radius.width > other.point.y
}

case class Grenade(coordinate: Coordinate) extends GamePlayer
case class PlayerTarget(coordinate: Coordinate) extends GamePlayer
