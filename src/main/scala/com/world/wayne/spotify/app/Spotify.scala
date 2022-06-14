package com.world.wayne.spotify.app

import com.world.wayne.spotify.app.utils.PrintResults
import com.world.wayne.spotify.common.Constants.{DEFAULT_PLAYLIST_ID, PLAYLIST_URL}
import com.world.wayne.spotify.endpoints.SpotifyPlaylistEndPoint

object Spotify extends App {
  run()

  def run(): Unit = {
    val fromSpotifyPlaylistEndPoint = SpotifyPlaylistEndPoint(apiBaseUrl = PLAYLIST_URL, playListId = DEFAULT_PLAYLIST_ID)
    PrintResults(fromSpotifyPlaylistEndPoint)
  }
}

