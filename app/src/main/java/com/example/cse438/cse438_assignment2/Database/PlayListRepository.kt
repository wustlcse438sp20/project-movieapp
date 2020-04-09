package com.example.cse438.cse438_assignment2.Database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayListRepository(private val playListDao: PlayListDao) {

    val allPlaylists: LiveData<List<PlayList>> = playListDao.getplaylists()


    fun insert(playList: PlayList) {
        CoroutineScope(Dispatchers.IO).launch {
            playListDao.insert(playList)
        }
    }




}