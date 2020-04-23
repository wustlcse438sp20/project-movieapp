package com.example.cse438.cse438_assignment2.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface TrackListDao {

    @Query("SELECT * from track_table")
    fun getTracks(): LiveData<List<TrackList>>

    @Insert
    fun insert(trackList: TrackList)

    @Query("SELECT * FROM track_table WHERE track_table.playlistid =:playlistID")
    fun gettracksinplaylist(playlistID: Int): LiveData<List<TrackList>>

    @Query("Delete FROM track_table WHERE track_table.playlistid =:playlistID and track_table.tracklistid =:tracklistID")
    fun deleteplaylist(playlistID: Int, tracklistID: Int)

    @Query("SELECT comment FROM track_table WHERE track_table.Trackname =:trackname")
    fun getComments(trackname: String): LiveData<List<String>>




}