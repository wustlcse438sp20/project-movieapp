package com.example.cse438.cse438_assignment2.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.cse438.cse438_assignment2.Data.Track

@Entity(tableName = "track_table")
data class TrackList(
    @ColumnInfo(name = "Playlistname") val Playlistname: String,
    @ColumnInfo(name = "Trackname") val trackname: String,
    @ColumnInfo(name = "Artist") val artist: String,
    @ColumnInfo(name = "Duration") val duration: Int,
    @ColumnInfo(name = "playlistid") val playlistid: Int)
{
    @PrimaryKey(autoGenerate = true)
    var tracklistid: Int = 0
}


