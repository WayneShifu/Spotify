package com.world.wayne.spotify.common.implicits

import play.api.libs.json.{JsObject, Json}
import scalaj.http.HttpResponse

object HttpImplicits {
  /**
   * Implicitly convert HttpResponse to Json Object
   *
   * Validate the response to ensure status code = 200
   */
  implicit val httpResponseToJsObject: HttpResponse[String] => JsObject = {
    case HttpResponse(body, 200, _) => Json.parse(body).as[JsObject]
    case HttpResponse(body, error, _) => throw new Exception(s"Error Occurred: $error, $body")
  }
}
