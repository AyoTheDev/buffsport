package com.buffup.app.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel(coroutineContextProvider: CoroutineContextProvider) :
    ViewModel(), CoroutineScope {

    private val jobs = mutableListOf<Job>()
    override val coroutineContext: CoroutineContext = coroutineContextProvider.io

    fun load(job: Job) {
        job.apply {
            jobs.add(this)
            this.invokeOnCompletion { jobs.remove(this) }
        }
    }

}