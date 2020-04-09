package com.example.cse438.cse438_assignment2.Database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class TrackListRepository(private val trackListDao: TrackListDao) {

    var alltracklists: LiveData<List<TrackList>> = trackListDao.getTracks()

    fun insert(trackList: TrackList) {
        CoroutineScope(Dispatchers.IO).launch {
            trackListDao.insert(trackList)
        }
    }

    fun gettracksinplaylist(playlistID: Int):LiveData<List<TrackList>> {
            return trackListDao.gettracksinplaylist(playlistID)
    }

    fun deleteplaylist(playlistID: Int, tracklistID: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            trackListDao.deleteplaylist(playlistID, tracklistID)
        }
    }


}