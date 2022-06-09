package com.world.wayne.spotify.common

object Constants {
  final val DEFAULT_PLAYLIST_ID = "37i9dQZEVXbMDoHDwVN2tF"
  final val BASE_API_URL = "https://api.spotify.com/v1"
  final val PLAYLIST_URL: String = s"$BASE_API_URL/playlists/"
  final val TRACK_URL: String = s"${BASE_API_URL}/tracks/"
  final val AUTH_URL: String = "https://accounts.spotify.com/api/token"
  final val AUTH_POST_FORM: Seq[(String, String)] = Seq("grant_type" -> "client_credentials")
  final val ENV_CLIENT_ID_KEY: String = "spotify-client-id"
  final val ENV_CLIENT_SECRET_KEY: String = "spotify-client-secret"
}
