package com.bonifasiustrg.newsapp.data

import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET(EndPoint.TOP_HEADLINE)
    suspend fun getTopHeadlines(
        @Query(QueryParam.COUNTRY) country: String = "id",
        @Query(QueryParam.API_KEY) apiKey: String = "318fff50119b4dacb62f55b5d4a3b29e",
        @Query(QueryParam.CATEGORY) category: String,
    ): NewsResponse

    object EndPoint {
        const val TOP_HEADLINE = "/v2/top-headlines"
    }

    object QueryParam {
        const val COUNTRY = "country"
        const val CATEGORY = "category"
        const val API_KEY = "apiKey"
    }


    companion object {
        const val BASE_URL = "https://newsapi.org"

        private fun create(): WebServices {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebServices::class.java)
        }

        fun modules(): Module {
            return module {
                single { create() }
            }
        }
    }
}