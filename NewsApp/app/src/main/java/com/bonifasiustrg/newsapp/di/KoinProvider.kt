package com.bonifasiustrg.newsapp.di

import android.content.Context
import com.bonifasiustrg.newsapp.data.WebServices
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinProvider {
    fun initKoin(context: Context) {
        startKoin{
            androidContext(context)
            modules (
                WebServices.modules()
            )
        }
    }
}