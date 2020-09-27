package com.buffup.sdk.services

import com.buffup.sdk.endpoints.Endpoints
import com.buffup.sdk.exceptions.ServerException
import com.buffup.sdk.model.Buff
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import javax.inject.Inject

class BuffSportApiService @Inject constructor(private val endpoints: Endpoints) {

    val buffs: Flow<Buff> = flow {
        for (i in 1..5) {
            delay(30000)
            endpoints.getBuff(i).body()?.result?.let { buff -> emit(buff) }
        }
    }.retry(2) {e -> throw ServerException(0, e.message) }
}