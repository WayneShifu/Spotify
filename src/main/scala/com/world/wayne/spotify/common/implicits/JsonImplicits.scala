package com.world.wayne.spotify.common.implicits

import com.world.wayne.spotify.model.auth.AccessToken
import com.world.wayne.spotify.model.endpoint.playlist.PlayListDataStore
import play.api.libs.json.{Json, Reads}

object JsonImplicits {
  implicit val authReadFormat: Reads[AccessToken] = Json.format[AccessToken]
  implicit val trackInfoImplicit: Reads[PlayListDataStore] = Json.format[PlayListDataStore]
}
