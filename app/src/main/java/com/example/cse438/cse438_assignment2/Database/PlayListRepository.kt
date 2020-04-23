package com.example.cse438.cse438_assignment2.Database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayListRepository(private val playListDao: PlayListDao, private val email: String) {

    val allPlaylists: LiveData<List<PlayList>> = playListDao.getplaylists(email)


    fun insert(playList: PlayList) {
        CoroutineScope(Dispatchers.IO).launch {
            playListDao.insert(playList)
        }
    }

    fun update(name:String, description:String, genre:String, rating:Int, id:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            playListDao.update(name, description, genre, rating, id)
        }
    }




}