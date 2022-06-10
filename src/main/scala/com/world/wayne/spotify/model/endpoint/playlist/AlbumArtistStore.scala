package com.world.wayne.spotify.model.endpoint.playlist

import play.api.libs.json.{JsString, JsValue}

case class AlbumArtistStore(
                        external_urls: JsValue,
                        href: JsString,
                        id: JsString,
                        name: JsString,
                        `type`: JsString,
                        uri: JsString
                      )
