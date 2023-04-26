package com.pwssv67.aviv.ui.info.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwssv67.aviv.model.repository.EstateRepository
import com.pwssv67.aviv.ui.info.state.EstateInfoViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EstateInfoViewModel(
    val id: Int,
    private val estateRepository: EstateRepository
) : ViewModel() {
    private val _state = MutableStateFlow<EstateInfoViewState>(EstateInfoViewState.Loading)
    val state = _state.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state.value = EstateInfoViewState.Error(throwable.message)
    }

    private var job: Job? = null

    init {
        load()
    }

    fun load() {
        job?.cancel()
        job = viewModelScope.launch(coroutineExceptionHandler) {
            _state.value = EstateInfoViewState.Loading
            val estateInfoFlow = estateRepository.getEstateInfo(id)
            estateInfoFlow.collect { estateInfo ->
                _state.value = estateInfo.fold(
                    { EstateInfoViewState.Success(it) },
                    { EstateInfoViewState.Error(it.message) }
                )
            }
        }
    }
}