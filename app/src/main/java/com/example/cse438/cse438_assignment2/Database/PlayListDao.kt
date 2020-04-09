package com.example.cse438.cse438_assignment2.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//define the DAO for data access to the table
@Dao
interface PlayListDao {

    @Query("SELECT * from playlist_table")
    fun getplaylists(): LiveData<List<PlayList>>

    @Insert
    fun insert(playList: PlayList)

}