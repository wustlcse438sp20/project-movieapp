package com.example.cse438.cse438_assignment2.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


//base retrofit client to define settings and url
object ApiClient {
    const val BASE_URL = "https://api.themoviedb.org/3/"

    fun makeRetrofitService(): MovieInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(MovieInterface::class.java)
    }

//    fun makeRetrofitServicewithParameter(url: String): TrackInterface {
//        return Retrofit.Builder()
//            .baseUrl(url)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build().create(TrackInterface::class.java)
//    }
}