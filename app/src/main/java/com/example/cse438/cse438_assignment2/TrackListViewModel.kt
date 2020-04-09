package com.example.cse438.cse438_assignment2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cse438.cse438_assignment2.Database.*
import kotlinx.coroutines.launch
import java.util.*

class TrackListViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: TrackListRepository
    var allTracklists: LiveData<List<TrackList>> = MutableLiveData()
    var matchtracklist: LiveData<List<TrackList>> = MutableLiveData()

    init {
        val tracklistsDao = TrackListRoomDatabase.getDatabase(application).tracklistDao()
        repository = TrackListRepository(tracklistsDao)
        allTracklists = repository.alltracklists

    }

    fun getTracks() : LiveData<List<TrackList>> {
        return allTracklists
    }

    //insert function on view model scope
    fun insert(trackList: TrackList) = viewModelScope.launch{
        repository.insert(trackList)
    }


    fun gettracksinplaylist(playlistID: Int) : LiveData<List<TrackList>> {
        matchtracklist = repository.gettracksinplaylist(playlistID)
        return matchtracklist
    }

    fun deleteplaylist(playlistID: Int, tracklistID: Int) = viewModelScope.launch{
        repository.deleteplaylist(playlistID, tracklistID)
    }


}