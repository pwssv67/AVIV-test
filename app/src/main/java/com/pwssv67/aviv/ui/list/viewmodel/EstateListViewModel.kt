package com.pwssv67.aviv.ui.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwssv67.aviv.model.repository.EstateRepository
import com.pwssv67.aviv.ui.list.state.EstateListViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EstateListViewModel(
    private val repository: EstateRepository
) : ViewModel() {

    private val _state = MutableStateFlow<EstateListViewState>(EstateListViewState.Loading)
    val state = _state.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state.value = EstateListViewState.Error(throwable.message)
    }

    private var job: Job? = null

    init {
        load()
    }

    fun load() {
        job?.cancel()
        job = viewModelScope.launch(coroutineExceptionHandler) {
            _state.value = EstateListViewState.Loading
            val estateListFlow = repository.getEstates()
            estateListFlow.collect { estateList ->
                _state.value = estateList.fold(
                    { EstateListViewState.Success(it) },
                    { EstateListViewState.Error(it.message) }
                )
            }
        }
    }
}