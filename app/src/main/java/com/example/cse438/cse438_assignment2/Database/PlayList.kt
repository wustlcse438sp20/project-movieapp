package com.example.cse438.cse438_assignment2.Database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cse438.cse438_assignment2.Data.Track

//create the sql table
@Entity(tableName = "watchlist_table")
data class PlayList(
                    @ColumnInfo(name = "name") val name: String,
                    @ColumnInfo(name = "description") val description: String,
                    @ColumnInfo(name="rating") val rating: Int,
                    @ColumnInfo(name="genre") val genre: String)
{
    @PrimaryKey(autoGenerate = true)
    var playlistid: Int = 0
}