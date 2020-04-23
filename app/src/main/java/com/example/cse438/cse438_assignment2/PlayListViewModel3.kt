package com.example.cse438.cse438_assignment2

import android.app.Application
import androidx.lifecycle.*
import com.example.cse438.cse438_assignment2.Database.*
import kotlinx.coroutines.launch

class PlayListViewModelFactory3(application: Application, email: String): ViewModelProvider.NewInstanceFactory(){
    private var mApplication = application
    private var mParam =email


    fun PlayListViewModelFactory3(
        application: Application,
        param: String
    ) {
        mApplication = application
        mParam = param
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayListViewModel3(mApplication,mParam) as T
    }
}

class PlayListViewModel3(application: Application, email: String) : AndroidViewModel(application) {
    // The ViewModel maintains a reference to the repository to get data.
    private val repository: PlayListRepository
    var allPlaylists: LiveData<List<PlayList>> = MutableLiveData()
    var email = email
    init {
        val playlistsDao = PlayListRoomDatabase.getDatabase(application).playlistDao()

        repository = PlayListRepository(playlistsDao,email)
        allPlaylists = repository.allPlaylists
    }

    fun getplaylists() : LiveData<List<PlayList>>{
        return allPlaylists
    }

    //insert function on view model scope
    fun insert(playList: PlayList) = viewModelScope.launch{
        repository.insert(playList)
    }

    fun update(name:String, description:String, genre:String, rating:Int, id:Int) = viewModelScope.launch{
        repository.update(name, description, genre, rating, id)
    }

}