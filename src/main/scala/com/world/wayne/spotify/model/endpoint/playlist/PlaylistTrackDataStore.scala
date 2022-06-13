package com.world.wayne.spotify.model.endpoint.playlist

import play.api.libs.json._

case class PlaylistTrackDataStore(
                         album: JsValue,
                         artists: JsArray,
                         available_markets: JsArray,
                         disc_number: JsNumber,
                         duration_ms: JsNumber,
                         episode: JsBoolean,
                         explicit: JsBoolean,
                         external_ids: JsValue,
                         external_urls: JsValue,
                         href: JsString,
                         id: JsString,
                         is_local: JsBoolean,
                         name: JsString,
                         popularity: JsNumber,
                         preview_url: Option[JsString],
                         track: JsBoolean,
                         track_number: JsNumber,
                         `type`: JsString,
                         uri: JsString
                       ) extends DataStore {
  val albumType: JsLookupResult = album \ "album_type"

  val albumArtist: Seq[ArtistDataStore] = (album \\ "artists").flatMap(artist => artist.as[JsArray].value.map(artist => artist.as[ArtistDataStore](Json.format[ArtistDataStore])))
  val playlistArtists: Seq[ArtistDataStore] = artists.as[JsArray].value.map(artist => artist.as[ArtistDataStore](Json.format[ArtistDataStore]))
  val albumAvailableMarketList: Seq[String] = (album \\ "available_markets").map(jv => jv.as[JsArray]).flatMap(markets => markets.value.map(market => market.as[JsString].value))
}
