package com.pwssv67.aviv.data

import com.pwssv67.aviv.data.mocks.FailingDataSource
import com.pwssv67.aviv.data.mocks.SuccessfulDataSource
import com.pwssv67.aviv.model.repository.EstateRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@Suppress("NAME_SHADOWING")
class EstateRepositoryTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun initCoroutines() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `ensure fetching estates works as expected`() = runTest(dispatchTimeoutMs = 1_000L)  {
        val repository = EstateRepositoryImpl(SuccessfulDataSource())
        val resultFlow = repository.getEstates()
        val result = resultFlow.firstOrNull()
        assert(result != null)
        assert(result?.isSuccess == true)
        assert(result?.getOrNull()?.estates?.isNotEmpty() == true)

        result?.let { result ->
            assert(result.isSuccess)
            assert(result.getOrNull()?.estates?.isNotEmpty() == true)
        } ?: assert(false) { "Result is null" }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `ensure errors are handled correctly when fetching estates`() = runTest(dispatchTimeoutMs = 1_000L)  {
        val repository = EstateRepositoryImpl(FailingDataSource())
        val resultFlow = repository.getEstates()
        try {
            val result = resultFlow.firstOrNull()
            assert(result != null)
            assert(result?.isFailure == true)
        } catch (e: Exception) {
            assert(false) { "Exception handle failed" }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `ensure fetching estate info works as expected`() = runTest(dispatchTimeoutMs = 1_000L)  {
        val repository = EstateRepositoryImpl(SuccessfulDataSource())
        val resultFlow = repository.getEstateInfo(1)
        val result = resultFlow.firstOrNull()
        result?.let { result ->
            assert(result.isSuccess)
            assert(result.getOrNull()?.estate?.id == 1)
        } ?: assert(false) { "Result is null" }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `ensure errors are handled correctly when fetching estate info`() = runTest(dispatchTimeoutMs = 1_000L)  {
        val repository = EstateRepositoryImpl(FailingDataSource())
        val resultFlow = repository.getEstateInfo(1)
        try {
            val result = resultFlow.firstOrNull()
            assert(result != null)
            assert(result?.isFailure == true)
        } catch (e: Exception) {
            assert(false) { "Exception handle failed" }
        }
    }
}