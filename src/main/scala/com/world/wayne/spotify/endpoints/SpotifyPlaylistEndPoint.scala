package com.world.wayne.spotify.endpoints

import com.world.wayne.spotify.auth.Auth
import com.world.wayne.spotify.common.implicits.HttpImplicits._
import com.world.wayne.spotify.common.implicits.JsonImplicits._
import com.world.wayne.spotify.custom.types.CustomOutputTypes
import com.world.wayne.spotify.endpoints.utils.HttpEndPoint.createEndPoint
import com.world.wayne.spotify.model.endpoint.playlist.{PlaylistDataStore, PlaylistTrackDataStore}
import play.api.libs.json.{JsObject, JsValue, Reads}
import scalaj.http.HttpResponse

import scala.reflect.ClassTag

case class SpotifyPlaylistEndPoint(apiBaseUrl: String, playListId: String) {
   def parseAndStorePlayListDataFromHttpResponseJsonWith[T: Reads](jsonObject: JsObject, customParseRule: Option[JsObject => Seq[JsValue]] = None)(implicit byUsingClassTag: ClassTag[T]): Seq[T] = {
    val JsObjectTransform: JsObject => Seq[JsValue] = customParseRule match {
      case Some(byUsingCustomDefinedRule) => byUsingCustomDefinedRule
      case _ => byUsingClassTag
    }
     JsObjectTransform(jsonObject).map(eachDataSet => eachDataSet.as[T])
  }

  private val apiUrl: String = apiBaseUrl + playListId
  lazy val playlistHttpResponseData: HttpResponse[String] = createEndPoint(apiUrl, Auth().accessToken)

  def outputPlaylistTrackLevelData[A <: CustomOutputTypes](outputSetter: Seq[PlaylistTrackDataStore] => Seq[A]): Seq[A] = {
    outputSetter(
      parseAndStorePlayListDataFromHttpResponseJsonWith[PlaylistTrackDataStore](playlistHttpResponseData)
    )
  }

  def outputPlaylistLevelData[A <: CustomOutputTypes](outputSetter: Seq[PlaylistDataStore] => Seq[A]): Seq[A] = {
    outputSetter(
      parseAndStorePlayListDataFromHttpResponseJsonWith[PlaylistDataStore](playlistHttpResponseData)
    )
  }
}
