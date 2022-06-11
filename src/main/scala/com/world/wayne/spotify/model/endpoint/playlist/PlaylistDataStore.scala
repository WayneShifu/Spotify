package com.world.wayne.spotify.model.endpoint.playlist

import play.api.libs.json.{JsArray, JsBoolean, JsString, JsValue}

case class PlaylistDataStore(
                         collaborative: JsBoolean,
                         description: JsString,
                         external_urls: JsValue,
                         followers: JsValue,
                         href: JsString,
                         id: JsString,
                         images: JsArray,
                         name: JsString,
                         owner: JsValue,
                         primary_color: Option[JsString],
                         public: JsBoolean,
                         snapshot_id: JsString,
                         tracks: JsValue,
                         `type`: JsString,
                         uri: JsString
                       ) {
  val imageUri: Seq[String] = (images \\ "url").map(url => url.as[JsString].value)
}
