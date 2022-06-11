package com.world.wayne.spotify.common.implicits

import com.world.wayne.spotify.common.Constants.{PLAYLIST_OVERALL_DATA_STORE_CLASS, PLAYLIST_TRACK_DATA_STORE_CLASS}
import com.world.wayne.spotify.model.auth.AccessToken
import com.world.wayne.spotify.model.endpoint.playlist.{PlaylistDataStore, PlaylistTrackDataStore}
import play.api.libs.json.{JsArray, JsObject, JsValue, Json, Reads}

import scala.reflect.ClassTag

object JsonImplicits {
  implicit val authReadFormat: Reads[AccessToken] = Json.format[AccessToken]
  implicit val trackInfoImplicit: Reads[PlaylistTrackDataStore] = Json.format[PlaylistTrackDataStore]
  implicit val playlistDataImplicit: Reads[PlaylistDataStore] = Json.format[PlaylistDataStore]

  implicit def trackJsonImplicit[T]: ClassTag[T] => JsObject => Seq[JsValue] = {
    case PLAYLIST_TRACK_DATA_STORE_CLASS => jo => (jo \ "tracks" \ "items").as[JsArray].value.map(tracks => (tracks \ "track").as[JsValue])
    case PLAYLIST_OVERALL_DATA_STORE_CLASS => jo => Seq(jo.as[JsValue])
    case _ => throw new NoSuchElementException("Unknown Data Store.  Please use Custom Parse Rule!")
  }
}
