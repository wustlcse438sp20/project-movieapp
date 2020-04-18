package com.example.cse438.cse438_assignment2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.Data.Chart
import com.example.cse438.cse438_assignment2.Data.Data
import com.example.cse438.cse438_assignment2.Data.Trending
import com.example.cse438.cse438_assignment2.network.MovieRepository
import com.example.cse438.cse438_assignment2.network.TrackRepository

class TrendViewModel(application: Application) : AndroidViewModel(application) {
    //live data and repository to track requests
    public var trendList: MutableLiveData<Trending> = MutableLiveData()
    public var searchList: MutableLiveData<Data> = MutableLiveData()
    public var trendRepository: MovieRepository = MovieRepository()

    //request to search for breweries
//    fun getTrackBySearch(param: String) {
//        trackRepository.getTrending(trackList, param)
//    }

    fun getTrend() {
        trendRepository.getTrending(trendList)
    }

    fun getMovieBySearch(param: String) {
        trendRepository.getMovieBySearch(trendList,param)
    }
}