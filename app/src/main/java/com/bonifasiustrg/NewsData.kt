package com.bonifasiustrg

import com.bonifasiustrg.newsappcompose.R

data class NewsData(
    val id: Int,
    val image: Int = R.drawable.ic_launcher_foreground,
    val author: String,
    val title: String,
    val description: String,
    val publishedAt: String
)
