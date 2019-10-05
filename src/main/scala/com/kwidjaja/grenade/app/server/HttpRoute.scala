package com.kwidjaja.grenade.app.server

import com.kwidjaja.grenade.app.util.JsonFormatUtil

import scala.concurrent.ExecutionContext

/**
  * Class to provide routes for Game Client.
  *
  * @author widjajk
  * @since 5/10/19 3:31 PM
  */
class HttpRoute(implicit val ec: ExecutionContext) extends JsonFormatUtil {}
