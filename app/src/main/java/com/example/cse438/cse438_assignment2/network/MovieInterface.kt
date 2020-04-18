package com.example.cse438.cse438_assignment2.network

import com.example.cse438.cse438_assignment2.Data.Chart
import com.example.cse438.cse438_assignment2.Data.Data
import com.example.cse438.cse438_assignment2.Data.Track
import com.example.cse438.cse438_assignment2.Data.Trending
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {

    //search by parameters
    @GET("search")
    suspend fun getTrackBySearch(@Query("q") q: String)
            : Response<Data>;

    @GET("trending/movie/week?api_key=8f589d13ad818bf33b851bb51b1184d6")
    suspend fun getTrending()
            : Response<Trending>

    @GET("search/movie?api_key=8f589d13ad818bf33b851bb51b1184d6")
    suspend fun getMovieBySearch(@Query("query") q: String)
            : Response<Trending>;


    @GET("chart")
    suspend fun getChart(): Response<Chart>;
}