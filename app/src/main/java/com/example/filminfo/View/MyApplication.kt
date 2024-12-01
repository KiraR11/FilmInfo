package com.example.filminfo.view

import android.app.Application
import com.example.filminfo.di.appModule
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}