package com.buffup.sdk.di

import com.buffup.sdk.endpoints.Endpoints
import com.buffup.sdk.services.BuffSportApiService
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {

    @Provides
    fun provideApiService(endpoints: Endpoints) = BuffSportApiService(endpoints)
}