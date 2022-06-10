package com.world.wayne.spotify.auth

import com.world.wayne.spotify.common.Constants.{AUTH_POST_FORM, AUTH_URL, ENV_CLIENT_ID_KEY, ENV_CLIENT_SECRET_KEY}
import com.world.wayne.spotify.common.implicits.HttpImplicits._
import com.world.wayne.spotify.common.implicits.JsonImplicits._
import com.world.wayne.spotify.model.auth.AccessToken
import play.api.libs.json.JsSuccess
import scalaj.http.{Base64, Http}

import scala.util.{Failure, Success, Try}


/**
 * Takes ClientID, Client Secret => Access Token
 *
 * @param clientId     Client ID
 * @param clientSecret Client Secret
 */
case class Auth(clientId: String, clientSecret: String) {

  val accessToken: String = {
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

/**
 * Get Client ID and Client Secret from Environment Variables and pass into Auth Case Class
 */
object Auth {
  def apply(): Auth = {
    getAndStoreClientIdClientSecret(ENV_CLIENT_ID_KEY, ENV_CLIENT_SECRET_KEY, key => sys.env(key))
  }

  private[auth] def getAndStoreClientIdClientSecret(envClientIdKey: String, envClientSecretKey: String, getter: String => String): Auth = {
    Try {
      val clientId = getter(envClientIdKey)
      val clientSecret = getter(envClientSecretKey)

      Auth(clientId, clientSecret)
    } match {
      case Success(credentials) => credentials
      case Failure(exception) => exception match {
        case _: NoSuchElementException => throw new Exception(s"Please set Environment Variables - ${exception.getMessage}")
        case _ => throw new Exception(exception.getMessage)
      }
    }
  }
}
