package com.buffup.app.di.component

import android.app.Application
import com.buffup.sdk.di.NetworkModule
import com.buffup.app.di.builder.ActivityBuilder
import com.buffup.app.di.module.ApplicationModule
import com.buffup.app.App
import com.buffup.app.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        ActivityBuilder::class
    ]
)
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun applicationModule(applicationModule: ApplicationModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): ApplicationComponent
    }

    override fun inject(app: App)
}