package com.world.wayne.spotify.model.auth

/**
 * Stores Access Token Info
 * @param access_token access token string from API
 * @param token_type token type from API
 * @param expires_in expires in millisecond from API
 */
case class AccessToken(
                        access_token: String,
                        token_type: String,
                        expires_in: Int
                      )
