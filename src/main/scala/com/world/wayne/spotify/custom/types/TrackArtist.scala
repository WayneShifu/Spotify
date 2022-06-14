package com.world.wayne.spotify.custom.types

import com.world.wayne.spotify.model.endpoint.playlist.PlaylistTrackDataStore

case class TrackArtist(trackNumber: BigDecimal, trackName: String, artistNames: String) extends CustomOutputTypes {
  override def toString: String = {
    s"${trackNumber}: Track Name: ${trackName} / Artists: ${artistNames}"
  }
}

object TrackArtist {
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
}
