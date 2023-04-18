package com.bonifasiustrg.newsapp.domain

import com.bonifasiustrg.newsapp.data.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    val topHeadlines: Flow<List<NewsResponse.Article>>
}