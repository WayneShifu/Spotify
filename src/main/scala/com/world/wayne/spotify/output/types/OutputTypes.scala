package com.world.wayne.spotify.output.types

import com.world.wayne.spotify.model.endpoint.playlist.PlayListDataStore

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

  val storeTrackArtist: Seq[PlayListDataStore] => Seq[TrackArtist] = {
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

  val storeAvailableMarket: Seq[PlayListDataStore] => Seq[TrackAvailableMarkets] = {
    playListData => playListData
      .map(
        playListDetails => TrackAvailableMarkets(playListDetails.name.value, playListDetails.albumAvailableMarketList.mkString(","))
      )
  }
}
