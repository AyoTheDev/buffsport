package com.buffup.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.buffup.app.common.TestContextProvider
import com.ayo.movies.utils.Resource
import com.buffup.app.ui.viewmodel.MainViewModel
import com.buffup.sdk.model.ApiResult
import com.buffup.sdk.model.Author
import com.buffup.sdk.model.Buff
import com.buffup.sdk.model.Question
import com.buffup.sdk.services.BuffSportApiService
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var underTest: MainViewModel

    @Mock
    lateinit var buffSportApiService: BuffSportApiService


    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        underTest = MainViewModel(TestContextProvider(), buffSportApiService)
    }

    @Test
    fun loadBuffsTest(): Unit = runBlocking {

        //given
        val buff = mockApiResult
        val observer: Observer<Resource<Buff>> = mock()
        whenever(buffSportApiService.getBuff(1)).doReturn(mockApiResult)

        //when
        underTest.buff.observeForever(observer)
        underTest.loadBuffs()
        //delay(30004)

        //then
        //verify(buffSportApiService).getBuff(1)
        verify(observer).onChanged(Resource.Loading(true))
        //verify(observer).onChanged(Resource.Success(mockApiResult.result))
    }


    private val mockApiResult =
        ApiResult(
            Buff(
                emptyList(),
                Author("first", "last"),
                1, "", 1, "", 1,
                Question(1, 1, ""), 1, 1
            )
        )

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

}

