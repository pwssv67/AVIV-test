package com.pwssv67.aviv.core.app

import android.app.Application
import com.pwssv67.aviv.core.di.rootDIModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(rootDIModule)
        }
    }
}