package com.buffup.app.di.module

import android.content.Context
import com.buffup.app.common.CoroutineContextProvider
import com.buffup.app.App
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val app: App){

    @Provides
    fun provideContext(): Context = app.applicationContext

    @Provides
    fun provideCoroutineContext(): CoroutineContextProvider {
        return CoroutineContextProvider()
    }
}