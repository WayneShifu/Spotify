package com.world.wayne.spotify.app

import com.world.wayne.spotify.common.Constants.{DEFAULT_PLAYLIST_ID, PLAYLIST_URL}
import com.world.wayne.spotify.endpoints.PlaylistEndPoint
import com.world.wayne.spotify.output.types.OutputTypes.{PlaylistSimpleData, TrackArtist, TrackAvailableMarkets, storeAvailableMarket, storePlaylistSimpleData, storeTrackArtist}

object Spotify extends App {
  run()

  def run(): Unit = {

    val endPoint = PlaylistEndPoint(playListId = DEFAULT_PLAYLIST_ID, apiBaseUrl = PLAYLIST_URL)

    endPoint
      .outputPlaylistTrackLevelData[TrackAvailableMarkets](
        fromPlaylistTrackStore => storeAvailableMarket(fromPlaylistTrackStore)
      ).collect { case markets: TrackAvailableMarkets => println(markets) }

    endPoint
      .outputPlaylistTrackLevelData[TrackArtist](
        fromPlaylistTrackStore => storeTrackArtist(fromPlaylistTrackStore)
      ).collect { case trackArtist: TrackArtist => println(trackArtist) }

    endPoint
      .outputPlaylistLevelData[PlaylistSimpleData](
        fromPlaylistDataStore => storePlaylistSimpleData(fromPlaylistDataStore)
      ).collect { case playlistSimpleData: PlaylistSimpleData => println(playlistSimpleData) }

  }
}
