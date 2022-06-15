package com.world.wayne.spotify.custom.types

import com.world.wayne.spotify.model.endpoint.playlist.PlaylistDataStore

case class PlaylistSimpleData(name: String, uri: String, description: String) extends CustomOutputTypes {
  override def toString: String = {
    s"Playlist Name: $name / Uri: $uri / Description: $description"
  }
}

object PlaylistSimpleData {
  lazy val storeData: Seq[PlaylistDataStore] => Seq[PlaylistSimpleData] = {
    allPlaylistData => allPlaylistData
      .map(
        eachPlaylistDataSet => PlaylistSimpleData(eachPlaylistDataSet.name.value, eachPlaylistDataSet.uri.value, eachPlaylistDataSet.description.value)
      )
  }
}