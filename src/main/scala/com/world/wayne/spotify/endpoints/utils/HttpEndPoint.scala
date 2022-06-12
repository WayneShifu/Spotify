package com.world.wayne.spotify.endpoints.utils

import scalaj.http.{Http, HttpResponse}

object HttpEndPoint {
  def createEndPoint(apiUrl: String, accessToken: String): HttpResponse[String] = {
    Http(apiUrl)
      .header("url", apiUrl)
      .header("Authorization", s"Bearer $accessToken")
      .asString
  }
}
