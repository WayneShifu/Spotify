package com.world.wayne.spotify.model.endpoint.playlist

import play.api.libs.json.{JsString, JsValue}

/**
 * Both for Artists and Album Artists level
 * @param external_urls
 * @param href
 * @param id
 * @param name
 * @param `type`
 * @param uri
 */
case class ArtistStore(
                        external_urls: JsValue,
                        href: JsString,
                        id: JsString,
                        name: JsString,
                        `type`: JsString,
                        uri: JsString
                      )
