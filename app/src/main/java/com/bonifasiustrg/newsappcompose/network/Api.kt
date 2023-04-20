package com.bonifasiustrg.newsappcompose.network

import com.bonifasiustrg.newsappcompose.utils.Constant
import retrofit2.Retrofit

object Api {


    private val retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .build()

    val retrofitService: NewsService by lazy {
        retrofit.create(NewsService::class.java)
    }
}