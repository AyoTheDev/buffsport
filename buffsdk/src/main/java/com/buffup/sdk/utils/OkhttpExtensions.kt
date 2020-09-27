package com.buffup.sdk.utils

import okhttp3.OkHttpClient

fun OkHttpClient.Builder.addQueryParameterInterceptor(name: String, value: String): OkHttpClient.Builder {

    addInterceptor {
        var request = it.request()
        val url = request.url.newBuilder().addQueryParameter(name, value).build()
        request = request.newBuilder().url(url).build()
        it.proceed(request)
    }

    return this
}