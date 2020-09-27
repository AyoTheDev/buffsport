package com.buffup.app

import com.buffup.sdk.di.NetworkModule
import com.buffup.app.di.module.ApplicationModule
import com.buffup.app.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
            .application(this)
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build()
    }
}