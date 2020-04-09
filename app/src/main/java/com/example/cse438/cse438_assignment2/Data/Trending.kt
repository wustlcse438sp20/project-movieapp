package com.example.cse438.cse438_assignment2.Data

//Movie
data class Trending (
    val page:Int,
    val results: TrendingData,
    val total_pages:Int,
    val total_results:Int
)