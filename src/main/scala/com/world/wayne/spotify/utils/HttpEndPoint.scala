package com.world.wayne.spotify.utils

import com.world.wayne.spotify.auth.Auth
import scalaj.http.{Http, HttpResponse}

object HttpEndPoint {
  def createEndPoint(apiUrl: String): HttpResponse[String] = {
    Http(apiUrl)
      .header("url", apiUrl)
      .header("Authorization", s"Bearer ${Auth().accessToken}")
      .asString
  }
}
