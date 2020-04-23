package com.example.cse438.cse438_assignment2.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.errorprone.annotations.Var

//define the DAO for data access to the table
@Dao
interface PlayListDao {

    @Query("SELECT * from watchlist_table where email = :userEmail")
    fun getplaylists(userEmail:String): LiveData<List<PlayList>>

    @Insert
    fun insert(playList: PlayList)

    @Query ("Update watchlist_table Set name = :name, description = :description, genre = :genre, rating = :rating where playlistid = :id")
    fun update(name:String, description:String, genre:String, rating:Int, id:Int)

}