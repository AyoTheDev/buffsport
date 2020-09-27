package com.buffup.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.buffup.app.common.TestContextProvider
import com.buffup.app.utils.Resource
import com.buffup.app.ui.viewmodel.MainViewModel
import com.buffup.sdk.model.ApiResult
import com.buffup.sdk.model.Author
import com.buffup.sdk.model.Buff
import com.buffup.sdk.model.Question
import com.buffup.sdk.services.BuffSportApiService
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
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

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var underTest: MainViewModel

    @Mock
    lateinit var buffSportApiService: BuffSportApiService


    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        underTest = MainViewModel(TestContextProvider(), buffSportApiService)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun streamBuffsTest(): Unit = runBlockingTest  {

        //given
        val observer: Observer<Resource<Buff>> = mock()
        whenever(buffSportApiService.buffs).doReturn(mockFlow)

        //when
        underTest.buff.observeForever(observer)
        underTest.streamBuffs()

        //then
        verify(observer).onChanged(Resource.Success(mockApiResult.result))
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    private val mockFlow: Flow<Buff> = flow { emit(mockApiResult.result) }

    private val mockApiResult =
        ApiResult(
            Buff(
                emptyList(),
                Author("first", "last"),
                1, "", 1, "", 1,
                Question(1, 1, ""), 1, 1
            )
        )


}

