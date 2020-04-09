package com.example.cse438_rest_studio

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.Data.Chart
import com.example.cse438.cse438_assignment2.Data.Data
import com.example.cse438.cse438_assignment2.network.TrackRepository

class TrackViewModel(application: Application) : AndroidViewModel(application) {

    //live data and repository to track requests
    public var trackList: MutableLiveData<Data> = MutableLiveData()
    public var chartList: MutableLiveData<Chart> = MutableLiveData()
    public var trackRepository: TrackRepository = TrackRepository()

    //request to search for breweries
    fun getTrackBySearch(param: String) {
        trackRepository.getTrackBySearch(trackList, param)
    }

    fun getChart() {
        trackRepository.getChart(chartList)
    }
}