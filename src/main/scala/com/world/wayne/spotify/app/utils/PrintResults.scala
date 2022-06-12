package com.world.wayne.spotify.app.utils

import com.world.wayne.spotify.custom.types.CustomOutputTypes.{PlaylistSimpleData, TrackArtist, TrackAvailableMarkets, storeAvailableMarket, storePlaylistSimpleData, storeTrackArtist}
import com.world.wayne.spotify.endpoints.SpotifyPlaylistEndPoint

object PrintResults {
  def apply(playlistEndPoint: SpotifyPlaylistEndPoint): Unit =  {
    playlistEndPoint
      .outputPlaylistTrackLevelData[TrackAvailableMarkets](
        fromPlaylistTrackStore => storeAvailableMarket(fromPlaylistTrackStore)
      ).collect { case markets: TrackAvailableMarkets => println(markets) }

    playlistEndPoint
      .outputPlaylistTrackLevelData[TrackArtist](
        fromPlaylistTrackStore => storeTrackArtist(fromPlaylistTrackStore)
      ).collect { case trackArtist: TrackArtist => println(trackArtist) }

    playlistEndPoint
      .outputPlaylistLevelData[PlaylistSimpleData](
        fromPlaylistDataStore => storePlaylistSimpleData(fromPlaylistDataStore)
      ).collect { case playlistSimpleData: PlaylistSimpleData => println(playlistSimpleData) }
  }
}
