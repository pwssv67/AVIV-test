package com.pwssv67.aviv.ui

import com.pwssv67.aviv.data.mocks.TestEstateRepository
import com.pwssv67.aviv.ui.list.state.EstateListViewState
import com.pwssv67.aviv.ui.list.viewmodel.EstateListViewModel
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
class EstateListViewModelTest {
    @Before
    fun initCoroutines() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `ensure viewModel starts as expected`() = runTest(dispatchTimeoutMs = 1_000L) {
        val repository = TestEstateRepository()
        //init viewModel
        val viewModel = EstateListViewModel(repository)
        assert(viewModel.state.value is EstateListViewState.Loading)

        //start loading
        viewModel.load()
        val loadingValue = viewModel.state.first()
        assert(loadingValue is EstateListViewState.Loading) { "${loadingValue::class.java} is not ${EstateListViewState.Loading::class.java}" }
        repository.emitEstateList(EstateGenerator.getEstateList())
        val firstValue = viewModel.state.first()
        assert(firstValue is EstateListViewState.Success) { "${firstValue::class.java} is not ${EstateListViewState.Success::class.java}" }

        //try reloading
        repository.resetCaches()
        viewModel.load()
        val loadingValue2 = viewModel.state.first()
        assert(loadingValue2 is EstateListViewState.Loading) { "${loadingValue2::class.java} is not ${EstateListViewState.Loading::class.java}" }
        repository.emitEstateList(EstateGenerator.getEstateList())
        val secondValue = viewModel.state.first()
        assert(secondValue is EstateListViewState.Success) { "${secondValue::class.java} is not ${EstateListViewState.Success::class.java}" }
        assert(firstValue != secondValue) { "first value should not be equal to second value" }
    }

    @Test
    fun `ensure viewModel handles errors properly`() = runTest(dispatchTimeoutMs = 1_000L) {
        val repository = TestEstateRepository()

        val viewModel = EstateListViewModel(repository)
        assert(viewModel.state.value is EstateListViewState.Loading)
        viewModel.load()
        try {
            repository.emitErrors()
            val state = viewModel.state.first()
            assert(state is EstateListViewState.Error) { "${state::class.java} is not ${EstateListViewState.Error::class.java}" }

            //try reloading
            repository.resetCaches()
            viewModel.load()
            repository.emitEstateList(EstateGenerator.getEstateList())
            val state2 = viewModel.state.first()
            assert(state2 is EstateListViewState.Success) { "${state2::class.java} is not ${EstateListViewState.Success::class.java}. Reloading after error should not lead to another error" }
        } catch (e: Exception) {
            assert(false) { "exception thrown: ${e.message}" }
        }
    }
}