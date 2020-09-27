package com.buffup.app.di.builder

import com.buffup.app.ui.activity.FullscreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {
    @ContributesAndroidInjector
    fun contributeActivity(): FullscreenActivity
}