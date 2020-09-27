package com.buffup.sdk.endpoints

import com.buffup.sdk.model.ApiResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoints {

    @GET("buffs/{id}")
    suspend fun getBuff(@Path("id") id: Int): Response<ApiResult>

}