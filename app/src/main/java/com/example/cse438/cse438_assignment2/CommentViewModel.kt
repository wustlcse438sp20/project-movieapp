package com.example.cse438.cse438_assignment2

import android.app.Application
import androidx.lifecycle.*
import com.example.cse438.cse438_assignment2.Database.*
import kotlinx.coroutines.launch
class CommentViewModelFactory(application: Application, moviename: String): ViewModelProvider.NewInstanceFactory(){
    private var mApplication = application
    private var mParam =moviename


    fun CommentViewModelFactory4(
        application: Application,
        param: String
    ) {
        mApplication = application
        mParam = param
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentViewModel(mApplication,mParam) as T
    }
}

class CommentViewModel(application: Application, moviename: String) : AndroidViewModel(application) {
    // The ViewModel maintains a reference to the repository to get data.
    private val repository: TrackListRepository
    var allComments: LiveData<List<String>> = MutableLiveData()
    var moviename = moviename
    init {
        val trackListDao = TrackListRoomDatabase.getDatabase(application).tracklistDao()

        repository = TrackListRepository(trackListDao)
        allComments = repository.getComment(moviename)
    }

    fun getComments() : LiveData<List<String>>{
        return allComments
    }


}