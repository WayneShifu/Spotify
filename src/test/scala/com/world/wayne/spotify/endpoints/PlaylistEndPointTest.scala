package com.world.wayne.spotify.endpoints

import org.scalatest.FlatSpec
import play.api.libs.json.{JsObject, JsString, JsValue, Json}

class PlaylistEndPointTest extends FlatSpec {
  case class TrackTest(id: JsString, name: JsString)

  it must "parse out JSon Object correctly into case class based on the fixed Json structure tracks-items-track" in {
    val jsonObj = Json.parse("{\"tracks\":{\"items\":[{\"track\":{\"id\":\"5BWl0bB1q0TqyFmkBEupZy\",\"name\":\"Hounds Of Love\"}}]}}").as[JsObject]

    val trackTest = PlaylistEndPoint("test", "test").parseAndStorePlayListDataFromHttpResponseJson[TrackTest](jsonObj)(Json.format[TrackTest])
    assert(trackTest == Seq(TrackTest(JsString("5BWl0bB1q0TqyFmkBEupZy"), JsString("Hounds Of Love"))))
  }
}
