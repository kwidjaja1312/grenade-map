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
    * Check if the first `c1` coordinate's radius covered the second `c2` coordinate's radius.
    *
    * @param other Other Component with coordinate to check against self.
    */
  def isInRadius(other: GameComponent): Boolean = {
    val inHorizontalRadius: Boolean =
      ((coordinate.point.x + coordinate.radius.range) > (other.coordinate.point.x + other.coordinate.radius.range)) &&
        ((other.coordinate.point.x + other.coordinate.radius.range) > (coordinate.point.x - coordinate.radius.range))

    val inVerticalRadius: Boolean =
      ((coordinate.point.y + coordinate.radius.range) > (other.coordinate.point.y + other.coordinate.radius.range)) &&
        ((other.coordinate.point.y + other.coordinate.radius.range) > (coordinate.point.y - coordinate.radius.range))

    inHorizontalRadius && inVerticalRadius
  }
}

case class GridMap(coordinate: Coordinate) extends GameComponent

case class Grenade(coordinate: Coordinate) extends GameComponent

case class Player(coordinate: Coordinate) extends GameComponent {

  /**
    * Change the self's coordinate to the new one.
    *
    * @param newCoordinate New Coordinate.
    */
  def moveTo(newCoordinate: Coordinate): Player = this.copy(coordinate.copy(point = newCoordinate.point))
}
