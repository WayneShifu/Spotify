package com.world.wayne.spotify.endpoints.utils

import com.world.wayne.spotify.common.Constants.{AUTH_POST_FORM, AUTH_URL}
import com.world.wayne.spotify.model.auth.AccessToken
import play.api.libs.json.{JsObject, JsSuccess}
import scalaj.http.{Base64, Http, HttpResponse}
import com.world.wayne.spotify.common.implicits.HttpImplicits._
import com.world.wayne.spotify.common.implicits.JsonImplicits._

object HttpEndPoint {
  def createEndPoint(apiUrl: String, accessToken: String): HttpResponse[String] = {
    Http(apiUrl)
      .header("url", apiUrl)
      .header("Authorization", s"Bearer $accessToken")
      .asString
  }

  def getAccessToken(clientId: String, clientSecret: String): String ={
    Http(AUTH_URL)
      .header("Authorization", "Basic " + Base64.encodeString(clientId + ":" + clientSecret))
      .postForm(AUTH_POST_FORM)
      .asString
      .validate[AccessToken] match {
      case JsSuccess(v, _) => v.access_token
      case _ => throw new Exception("Error while getting access token!")
    }
  }
}
