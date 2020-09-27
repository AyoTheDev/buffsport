package com.buffup.sdk.services

import com.buffup.sdk.endpoints.Endpoints
import javax.inject.Inject

class BuffSportApiService @Inject constructor(private val endpoints: Endpoints) {

    suspend fun getBuff(id: Int) = endpoints.getBuff(id).body()
}