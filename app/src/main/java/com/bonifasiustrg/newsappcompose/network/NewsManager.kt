package com.bonifasiustrg.newsappcompose.network

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bonifasiustrg.newsappcompose.models.TopNewsResponse
import com.bonifasiustrg.newsappcompose.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsManager {

    // underscore mean only use the variable in this class
    private val _newsResponse = mutableStateOf(TopNewsResponse())
    val newsResponse: State<TopNewsResponse>
        @Composable get() = remember {
            _newsResponse
        }

    init {
        getArticles() // will be used whenever NewsManager class is initialized
    }

    private fun getArticles() {
        val service = Api.retrofitService.getTopArticles("us", apiKey = Constant.API_KEY)
        // asynchronously sent the request and notify callback of its response or if an error occured
        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful) {
                    _newsResponse.value = response.body()!!
                    Log.d("news", "${_newsResponse.value}")
                } else {
                    Log.d("error", "${response.errorBody()}")

                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error", "${t.printStackTrace()}")

            }

        })
    }
}