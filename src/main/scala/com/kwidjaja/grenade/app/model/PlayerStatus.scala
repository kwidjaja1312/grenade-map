package com.kwidjaja.grenade.app.model

/**
  * Trait to defined types of Player's Status
  *
  * @author widjajk
  * @since 7/10/19 10:53 AM
  */
sealed trait PlayerStatus {
  val statusMsg: String
}

case object PlayerKilled extends PlayerStatus {
  override val statusMsg: String = "Player Killed"
}

case object UnableToSpawnPlayer extends PlayerStatus {
  override val statusMsg: String = "Unable to spawn Person in that location"
}
