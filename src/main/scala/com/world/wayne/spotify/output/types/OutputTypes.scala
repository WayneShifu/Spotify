package com.world.wayne.spotify.output.types

import com.world.wayne.spotify.model.endpoint.playlist.{PlaylistDataStore, PlaylistTrackDataStore}

trait OutputTypes

object OutputTypes {
  case class TrackArtist(trackNumber: BigDecimal, trackName: String, artistNames: String) extends OutputTypes {
    override def toString: String = {
      s"${trackNumber}: Track Name: ${trackName} / Artists: ${artistNames}"
    }
  }
  case class TrackAvailableMarkets(trackName: String, availableMarkets: String) extends OutputTypes {
    override def toString: String = {
      s"Track Name: ${trackName} / Markets: ${availableMarkets}"
    }
  }

  case class PlaylistSimpleData(name: String, uri: String, description: String) extends OutputTypes {
    override def toString: String = {
      s"Playlist Name: $name / Uri: $uri / Desription: $description"
    }
  }

  val storeTrackArtist: Seq[PlaylistTrackDataStore] => Seq[TrackArtist] = {
    playListData =>
      playListData
        .sortBy(t => t.track_number.value)
        .map(playListDetails =>
          TrackArtist(
            playListDetails.track_number.value,
            playListDetails.name.value,
            playListDetails.albumArtist.map(artist => artist.name.value).mkString(",")
          )
        )
  }

  val storeAvailableMarket: Seq[PlaylistTrackDataStore] => Seq[TrackAvailableMarkets] = {
    playListData => playListData
      .map(
        playListDetails => TrackAvailableMarkets(playListDetails.name.value, playListDetails.albumAvailableMarketList.mkString(","))
      )
  }

  val storePlaylistSimpleData: Seq[PlaylistDataStore] => Seq[PlaylistSimpleData] = {
    playlistData => playlistData
      .map(
        playlistDetails => PlaylistSimpleData(playlistDetails.name.value, playlistDetails.uri.value, playlistDetails.description.value)
      )
  }
}
