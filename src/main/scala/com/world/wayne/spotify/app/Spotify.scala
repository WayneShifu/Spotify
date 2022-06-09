package com.world.wayne.spotify.app

import com.world.wayne.spotify.common.Constants.{DEFAULT_PLAYLIST_ID, PLAYLIST_URL}
import com.world.wayne.spotify.endpoints.PlaylistEndPoint
import com.world.wayne.spotify.output.types.OutputTypes.{TrackArtist, TrackAvailableMarkets, storeAvailableMarket, storeTrackArtist}

object Spotify extends App {
  run()

  def run(): Unit = {
    PlaylistEndPoint(playListId = DEFAULT_PLAYLIST_ID, apiBaseUrl = PLAYLIST_URL)
      .getPlayListData(
        playListData => storeAvailableMarket(playListData)
      ).collect { case markets: TrackAvailableMarkets => println(markets) }

    PlaylistEndPoint(playListId = DEFAULT_PLAYLIST_ID, apiBaseUrl = PLAYLIST_URL)
      .getPlayListData(
        playListData => storeTrackArtist(playListData)
      ).collect { case trackArtist: TrackArtist => println(trackArtist) }
  }
}
