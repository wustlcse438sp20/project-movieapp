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
    @ColumnInfo(name = "playlistid") val playlistid: Int,
    @ColumnInfo(name = "rate") val rate: Int,
    @ColumnInfo(name = "comment") val comment: String
) {
    @PrimaryKey(autoGenerate = true)
    var tracklistid: Int = 0
}


