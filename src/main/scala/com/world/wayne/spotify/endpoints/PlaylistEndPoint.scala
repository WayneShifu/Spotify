package com.world.wayne.spotify.endpoints

import com.world.wayne.spotify.auth.Auth
import com.world.wayne.spotify.utils.HttpEndPoint.createEndPoint
import com.world.wayne.spotify.common.implicits.HttpImplicits._
import com.world.wayne.spotify.common.implicits.JsonImplicits._
import com.world.wayne.spotify.model.endpoint.playlist.PlayListDataStore
import com.world.wayne.spotify.output.types.OutputTypes
import play.api.libs.json.{JsArray, JsObject, JsValue, Reads}

case class PlaylistEndPoint(apiBaseUrl: String, playListId: String) {
  private [endpoints] def parseAndStorePlayListDataFromHttpResponseJson[T: Reads](jsonObject: JsObject): Seq[T] = {
    val trackJs: JsObject => Seq[JsValue] = jo => (jo \ "tracks" \ "items").as[JsArray].value.map(tracks => (tracks \ "track").as[JsValue])
    trackJs(jsonObject).map(track => track.as[T])
  }

  /**
   * Get PlayList Data by applying function pass in by the caller
   *
   * @param outputSetter function to parse the PlayList Case Class and store to the output type
   * @tparam A Type A
   * @return Type A
   */
  def getPlayListDataFromStore[A <: Seq[OutputTypes]](outputSetter: Seq[PlayListDataStore] => A): A = {
    val apiUrl: String = apiBaseUrl + playListId

    val playListDataStore = parseAndStorePlayListDataFromHttpResponseJson[PlayListDataStore](
      createEndPoint(apiUrl, Auth().accessToken)
    )
    outputSetter(playListDataStore)
  }
}
