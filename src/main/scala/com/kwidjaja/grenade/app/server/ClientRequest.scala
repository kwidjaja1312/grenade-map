package com.kwidjaja.grenade.app.server

import com.kwidjaja.grenade.app.model.{Grenade, Player}

/**
  * Class to be sent as HTTP Request.
  *
  * @author widjajk
  * @since 5/10/19 3:31 PM
  */
case class ClientRequest(grenades: List[Grenade], player: Option[Player] = None)
