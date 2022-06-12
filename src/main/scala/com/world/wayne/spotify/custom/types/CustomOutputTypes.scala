package com.world.wayne.spotify.custom.types

import com.world.wayne.spotify.model.endpoint.playlist.{PlaylistDataStore, PlaylistTrackDataStore}

trait CustomOutputTypes

object CustomOutputTypes {
  case class TrackArtist(trackNumber: BigDecimal, trackName: String, artistNames: String) extends CustomOutputTypes {
    override def toString: String = {
      s"${trackNumber}: Track Name: ${trackName} / Artists: ${artistNames}"
    }
  }
  case class TrackAvailableMarkets(trackName: String, availableMarkets: String) extends CustomOutputTypes {
    override def toString: String = {
      s"Track Name: ${trackName} / Markets: ${availableMarkets}"
    }
  }

  case class PlaylistSimpleData(name: String, uri: String, description: String) extends CustomOutputTypes {
    override def toString: String = {
      s"Playlist Name: $name / Uri: $uri / Desription: $description"
    }
  }

  lazy val storeTrackArtist: Seq[PlaylistTrackDataStore] => Seq[TrackArtist] = {
    allPlaylistTrackData =>
      allPlaylistTrackData
        .sortBy(t => t.track_number.value)
        .map(eachPlaylistTrackDataSet =>
          TrackArtist(
            eachPlaylistTrackDataSet.track_number.value,
            eachPlaylistTrackDataSet.name.value,
            eachPlaylistTrackDataSet.playlistArtists.map(artist => artist.name.value).mkString(", ")
          )
        )
  }

  lazy val storeAvailableMarket: Seq[PlaylistTrackDataStore] => Seq[TrackAvailableMarkets] = {
    allPlaylistTrackData => allPlaylistTrackData
      .map(
        eachPlaylistTrackDataSet => TrackAvailableMarkets(eachPlaylistTrackDataSet.name.value, eachPlaylistTrackDataSet.albumAvailableMarketList.mkString(","))
      )
  }

  lazy val storePlaylistSimpleData: Seq[PlaylistDataStore] => Seq[PlaylistSimpleData] = {
    allPlaylistData => allPlaylistData
      .map(
        eachPlaylistDataSet => PlaylistSimpleData(eachPlaylistDataSet.name.value, eachPlaylistDataSet.uri.value, eachPlaylistDataSet.description.value)
      )
  }
}
