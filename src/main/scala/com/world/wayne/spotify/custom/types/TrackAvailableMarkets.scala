package com.world.wayne.spotify.custom.types

import com.world.wayne.spotify.model.endpoint.playlist.PlaylistTrackDataStore


case class TrackAvailableMarkets(trackName: String, availableMarkets: String) extends CustomOutputTypes {
  override def toString: String = {
    s"Track Name: ${trackName} / Markets: ${availableMarkets}"
  }
}

object TrackAvailableMarkets {
  lazy val storeData: Seq[PlaylistTrackDataStore] => Seq[TrackAvailableMarkets] = {
    allPlaylistTrackData => allPlaylistTrackData
      .map(
        eachPlaylistTrackDataSet => TrackAvailableMarkets(eachPlaylistTrackDataSet.name.value, eachPlaylistTrackDataSet.albumAvailableMarketList.mkString(","))
      )
  }
}