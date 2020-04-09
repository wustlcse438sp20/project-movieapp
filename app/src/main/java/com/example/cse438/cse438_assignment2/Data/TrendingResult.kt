package com.example.cse438.cse438_assignment2.Data

//Movie
data class TrendingResult(
    val id: Int,
    val video: Boolean,
    val vote_count: Int,
    val vote_average: Double,
    val title: String,
    val release_date: String,
    val original_language: String,
    val original_title: String,
    val genre_ids: Array<Int>,
    val backdrop_path: String,
    val adult: Boolean,
    val overview: String,
    val poster_path: String,
    val popularity: Double,
    val media_type: String
)