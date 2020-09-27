package com.ayo.movies.utils

import java.lang.Exception

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure<T>(val msg: String, val exception: Exception? = null) : Resource<T>()
    data class Loading<T>(val loading: Boolean) : Resource<T>()
}