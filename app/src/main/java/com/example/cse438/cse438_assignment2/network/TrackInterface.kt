package com.example.cse438.cse438_assignment2.network

import com.example.cse438.cse438_assignment2.Data.Chart
import com.example.cse438.cse438_assignment2.Data.Data
import com.example.cse438.cse438_assignment2.Data.Track
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrackInterface {

    //search by parameters
    @GET("search")
    suspend fun getTrackBySearch(@Query("q") q: String)
            : Response<Data>;

    @GET("chart")
    suspend fun getChart(): Response<Chart>;

//    suspend fun getDetails(input: String): Response<Track>{
//        @GET(input)
//        suspend fun getDetails(): Response<Track>;
//
//    }
}

