package com.world.wayne.spotify.endpoints

import com.world.wayne.spotify.auth.Auth
import com.world.wayne.spotify.common.implicits.HttpImplicits._
import com.world.wayne.spotify.common.implicits.JsonImplicits._
import com.world.wayne.spotify.custom.types.CustomOutputTypes
import com.world.wayne.spotify.endpoints.utils.HttpEndPoint.createEndPoint
import com.world.wayne.spotify.model.endpoint.playlist.{DataStore, PlaylistDataStore, PlaylistTrackDataStore}
import play.api.libs.json.{JsObject, JsValue, Reads}
import scalaj.http.HttpResponse

import scala.reflect.ClassTag

case class SpotifyPlaylistEndPoint(apiBaseUrl: String, playListId: String) {
  /**
   * It reads the Json Object and save the Dataset to T
   *
   * [T : Reads] implies that it is implicitly define Reads[T] when saving eachDataSet.as[T]
   * @param jsonObject
   * @param customParseRule Optional custom Parse Rule can be passed in, otherwise it uses the ClassTag to determine the Parse Rule
   * @param byUsingClassTag ClasTag[T] - Please see the implicit defined in [[implicitlyTransformClassTagToTransformationFunction]]
   * @tparam T DataStore case class
   * @return
   */
   def parseAndStorePlayListDataFromHttpResponseJsonWith[T <: DataStore : Reads](jsonObject: JsObject, customParseRule: Option[JsObject => Seq[JsValue]] = None)(implicit byUsingClassTag: ClassTag[T]): Seq[T] = {
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
