package com.example.filminfo.view

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.filminfo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule) // Замените на ваши модули
        }
    }
}