package com.bonifasiustrg.newsapp

import android.app.Application
import com.bonifasiustrg.newsapp.di.KoinProvider

class NewsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        KoinProvider.initKoin(this)
    }
}