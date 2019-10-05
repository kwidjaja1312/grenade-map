package com.kwidjaja.grenade.app.model

/**
  * Class to store the states of the Game.
  *
  * @param grenades List of grenades. Can be empty.
  * @param players List of players. Can be empty.
  *
  * @author widjajk
  * @since 5/10/19 4:15 PM
  */
case class GameStore(grenades: List[Grenade], players: List[Player]) {

  def acceptGrenade(grenade: Grenade): GameStore = this.copy(grenade :: grenades, players)

  def acceptPlayer(player: Player): GameStore = this.copy(grenades, player :: players)

  def clear: GameStore = this.copy(List.empty, List.empty)
}

/** Companion object of [[GameStore]] */
object GameStore {
  def apply(grenade: Grenade, player: Player): GameStore = new GameStore(List(grenade), List(player))
}
