package com.buffup.app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.buffup.sdk.exceptions.NoNetworkException
import com.buffup.sdk.exceptions.ServerException
import com.buffup.sdk.model.Buff
import com.buffup.sdk.services.BuffSportApiService
import com.buffup.app.common.CoroutineContextProvider
import com.buffup.app.utils.Resource
import com.buffup.app.utils.Resource.Success
import com.buffup.app.common.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    private val buffSportApiService: BuffSportApiService
) : BaseViewModel(coroutineContextProvider) {

    val buff: LiveData<Resource<Buff>>
        get() = _buff

    private val _buff = MutableLiveData<Resource<Buff>>()

    init {
        _buff.postValue(Resource.Loading(true))
    }

    fun streamBuffs() = load(viewModelScope.launch {
        try {
            buffSportApiService.buffs.collect { buff -> _buff.postValue(Success(buff)) }
        } catch (e: NoNetworkException) {
            _buff.postValue(Resource.Failure("Please connect to the internet", e))
            Timber.e(e)
        } catch (e: ServerException) {
            _buff.postValue(Resource.Failure("Service is currently down", e))
            Timber.e(e)
        } catch (e: Exception) {
            _buff.postValue(Resource.Failure("Problem fetching buffs", e))
            Timber.e(e)
        }
    })
}