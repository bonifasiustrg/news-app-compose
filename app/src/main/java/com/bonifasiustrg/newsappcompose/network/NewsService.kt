package com.bonifasiustrg.newsappcompose.network

import com.bonifasiustrg.newsappcompose.models.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    fun getTopArticles(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<TopNewsResponse>
}