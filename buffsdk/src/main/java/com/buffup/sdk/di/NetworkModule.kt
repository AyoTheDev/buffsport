package com.buffup.sdk.di

import android.content.Context
import com.buffup.sdk.endpoints.Endpoints
import com.buffup.sdk.interceptors.NetworkConnectivityInterceptor
import com.buffup.sdk.interceptors.NetworkResponseInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    companion object {
        private const val TIME_OUT = 30L
        private const val BASE_URL = "http://demo2373134.mockable.io/"
    }

    @Provides
    fun provideEndpoint(retrofit: Retrofit): Endpoints {
        return retrofit.create(Endpoints::class.java)
    }

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkConnectivityInterceptor(context))
            .addInterceptor(NetworkResponseInterceptor())
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()
    }
}