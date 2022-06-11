package com.world.wayne.spotify.endpoints

import com.world.wayne.spotify.auth.Auth
import com.world.wayne.spotify.utils.HttpEndPoint.createEndPoint
import com.world.wayne.spotify.common.implicits.HttpImplicits._
import com.world.wayne.spotify.common.implicits.JsonImplicits._
import com.world.wayne.spotify.model.endpoint.playlist.{PlaylistDataStore, PlaylistTrackDataStore}
import com.world.wayne.spotify.output.types.OutputTypes
import play.api.libs.json.{JsObject, JsValue, Reads}
import scalaj.http.HttpResponse

import scala.reflect.ClassTag

case class PlaylistEndPoint(apiBaseUrl: String, playListId: String) {
   def parseAndStorePlayListDataFromHttpResponseJson[T: Reads](jsonObject: JsObject, customParseRule: Option[JsObject => Seq[JsValue]] = None)(implicit runtimeTag: ClassTag[T]): Seq[T] = {
    val trackJs: JsObject => Seq[JsValue] = customParseRule match {
      case Some(r) => r
      case _ => runtimeTag
    }
    trackJs(jsonObject).map(track => track.as[T])
  }

  private val apiUrl: String = apiBaseUrl + playListId
  lazy val playlistHttpResponseData: HttpResponse[String] = createEndPoint(apiUrl, Auth().accessToken)

  def outputPlaylistTrackLevelData[A <: OutputTypes](outputSetter: Seq[PlaylistTrackDataStore] => Seq[A]): Seq[A] = {
    outputSetter(
      parseAndStorePlayListDataFromHttpResponseJson[PlaylistTrackDataStore](playlistHttpResponseData)
    )
  }

  def outputPlaylistLevelData[A <: OutputTypes](outputSetter: Seq[PlaylistDataStore] => Seq[A]): Seq[A] = {
    outputSetter(
      parseAndStorePlayListDataFromHttpResponseJson[PlaylistDataStore](playlistHttpResponseData)
    )
  }
}
