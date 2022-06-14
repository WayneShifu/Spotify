package com.world.wayne.spotify.app.utils

import com.world.wayne.spotify.endpoints.SpotifyPlaylistEndPoint
import com.world.wayne.spotify.model.endpoint.playlist.{PlaylistDataStore, PlaylistTrackDataStore}
import com.world.wayne.spotify.custom.types.{PlaylistSimpleData, TrackArtist, TrackAvailableMarkets}
import com.world.wayne.spotify.common.implicits.JsonImplicits._

object PrintResults {
  def apply(playlistEndPoint: SpotifyPlaylistEndPoint): Unit =  {
    playlistEndPoint
      .outputPlaylistData[PlaylistTrackDataStore, TrackAvailableMarkets](
        fromPlaylistTrackStore => TrackAvailableMarkets.storeAvailableMarket(fromPlaylistTrackStore)
      ).collect { case markets: TrackAvailableMarkets => println(markets) }

    playlistEndPoint
      .outputPlaylistData[PlaylistTrackDataStore, TrackArtist](
        fromPlaylistTrackStore => TrackArtist.storeTrackArtist(fromPlaylistTrackStore)
      ).collect { case trackArtist: TrackArtist => println(trackArtist) }

    playlistEndPoint
      .outputPlaylistData[PlaylistDataStore, PlaylistSimpleData](
        fromPlaylistDataStore => PlaylistSimpleData.storePlaylistSimpleData(fromPlaylistDataStore)
      ).collect { case playlistSimpleData: PlaylistSimpleData => println(playlistSimpleData) }
  }
}
