package com.world.wayne.spotify.utils

import com.world.wayne.spotify.common.Constants.{DEFAULT_PLAYLIST_ID, PLAYLIST_URL}
import com.world.wayne.spotify.utils.HttpEndPoint.createEndPoint
import org.scalatest.FlatSpec
import play.api.libs.json.{JsString, Json}

class HttpEndPointTest extends FlatSpec{
  it must "complains about the access token not correct" in {
    val response =
    createEndPoint(PLAYLIST_URL + DEFAULT_PLAYLIST_ID, "dummyToken")

    val message: String = (Json.parse(response.body) \ "error" \ "message").as[JsString].value
    assert(message == "Invalid access token" && response.code == 401)
  }
}
