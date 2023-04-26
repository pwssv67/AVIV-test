package com.pwssv67.aviv.ui

import com.pwssv67.aviv.data.mocks.TestEstateRepository
import com.pwssv67.aviv.ui.info.state.EstateInfoViewState
import com.pwssv67.aviv.ui.info.viewmodel.EstateInfoViewModel
import com.pwssv67.aviv.utils.preview.EstateGenerator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EstateInfoViewModelTest {

    @Before
    fun initCoroutines() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `test loading info`() = runTest(dispatchTimeoutMs = 1_000L)  {
        val repository = TestEstateRepository()
        //1 is just a random id here, it doesn't matter, and is never checked :)
        val viewModel = EstateInfoViewModel(1, repository)

        val state = viewModel.state.value
        assert(state is EstateInfoViewState.Loading)
        viewModel.load()
        repository.emitEstateInfo(EstateGenerator.getEstateInfo(1))
        val state2 = viewModel.state.first()
        assert(state2 is EstateInfoViewState.Success)

        //try reloading
        repository.resetCaches()
        viewModel.load()
        val state3 = viewModel.state.first()
        assert(state3 is EstateInfoViewState.Loading)
        repository.emitEstateInfo(EstateGenerator.getEstateInfo(1))
        val state4 = viewModel.state.first()
        assert(state4 is EstateInfoViewState.Success)
    }

    @Test
    fun `test error handling in viewModel`() = runTest(dispatchTimeoutMs = 1_000L)  {
        val repository = TestEstateRepository()
        //1 is just a random id here, it doesn't matter, and is never checked :)
        val viewModel = EstateInfoViewModel(1, repository)

        val state = viewModel.state.value
        assert(state is EstateInfoViewState.Loading)
        viewModel.load()
        repository.emitErrors()
        val state2 = viewModel.state.first()
        assert(state2 is EstateInfoViewState.Error)

        //try reloading
        repository.resetCaches()
        viewModel.load()
        val state3 = viewModel.state.first()
        assert(state3 is EstateInfoViewState.Loading)
        repository.emitEstateInfo(EstateGenerator.getEstateInfo(1))
        val state4 = viewModel.state.first()
        assert(state4 is EstateInfoViewState.Success)
    }
}