package com.droid.bigbang

import android.app.Application
import com.bumptech.glide.request.target.ViewTarget
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(applicationModule, photoViewModel))
        }
        ViewTarget.setTagId(R.id.glideTag)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}