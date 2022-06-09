package com.world.wayne.spotify.endpoints

import com.world.wayne.spotify.utils.HttpEndPoint.createEndPoint
import com.world.wayne.spotify.common.implicits.HttpImplicits._
import com.world.wayne.spotify.common.implicits.JsonImplicits._
import com.world.wayne.spotify.model.endpoint.playlist.PlayListData
import com.world.wayne.spotify.output.types.OutputTypes
import com.world.wayne.spotify.output.types.OutputTypes.{TrackArtist, TrackAvailableMarkets}
import play.api.libs.json.{JsArray, JsObject, JsValue, Reads}

case class PlaylistEndPoint(apiBaseUrl: String, playListId: String) {
  private def retrievePlayListData[T: Reads](jsonObject: JsObject): Seq[T] = {
    val trackJs: JsObject => Seq[JsValue] = jo => (jo \ "tracks" \ "items").as[JsArray].value.map(tracks => (tracks \ "track").as[JsValue])
    trackJs(jsonObject).map(track => track.as[T])
  }

  /**
   * Get PlayList Data by applying function pass in by the caller
   *
   * @param f function to parse the PlayList Case Class
   * @tparam A Type A
   * @return Type A
   */
  def getPlayListData[A](f: Seq[PlayListData] => A): A = {
    val apiUrl: String = apiBaseUrl + playListId

    f(retrievePlayListData[PlayListData](createEndPoint(apiUrl)))
  }
}
