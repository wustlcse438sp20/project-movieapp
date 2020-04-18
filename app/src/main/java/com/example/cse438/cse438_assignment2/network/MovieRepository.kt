package com.example.cse438.cse438_assignment2.network

import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.Data.Chart
import com.example.cse438.cse438_assignment2.Data.Data
import com.example.cse438.cse438_assignment2.Data.Track
import com.example.cse438.cse438_assignment2.Data.Trending
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response


class MovieRepository {
    //get the instance of retrofit
    val service = ApiClient.makeRetrofitService()

    fun getTrending(resBody: MutableLiveData<Trending>) {
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<Trending>

            response = service.getTrending()

            //when the coroutine finishes
            withContext(Dispatchers.Main) {
                try {
                    //success case
                    if (response.isSuccessful) {
                        println(" is the size")
                        resBody.value = response.body()

                    } else {
                        //response error
                        println("HTTP error")
                    }
                } catch (e: HttpException) {
                    //http exception
                    println("HTTP Exception")
                } catch (e: Throwable) {
                    //error
                    println("Error")
                }
            }
        }
    }

    fun getMovieBySearch(resBody: MutableLiveData<Trending>, param: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<Trending>

            response = service.getMovieBySearch(param)

            //when the coroutine finishes
            withContext(Dispatchers.Main) {
                try {
                    //success case
                    if (response.isSuccessful) {
                        println(" is the size")
                        resBody.value = response.body()

                    } else {
                        //response error
                        println("HTTP error")
                    }
                } catch (e: HttpException) {
                    //http exception
                    println("HTTP Exception")
                } catch (e: Throwable) {
                    //error
                    println("Error")
                }
            }
        }
    }

}