package com.example.cse438.cse438_assignment2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cse438.cse438_assignment2.Database.PlayList
import com.example.cse438.cse438_assignment2.Database.PlayListRepository
import com.example.cse438.cse438_assignment2.Database.PlayListRoomDatabase
import kotlinx.coroutines.launch

class PlayListViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: PlayListRepository
    var allPlaylists: LiveData<List<PlayList>> = MutableLiveData()

    init {
        val playlistsDao = PlayListRoomDatabase.getDatabase(application).playlistDao()
        repository = PlayListRepository(playlistsDao)
        allPlaylists = repository.allPlaylists
    }

    fun getplaylists() : LiveData<List<PlayList>>{
        return allPlaylists
    }

    //insert function on view model scope
    fun insert(playList: PlayList) = viewModelScope.launch{
        repository.insert(playList)
    }

}