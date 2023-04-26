package com.pwssv67.aviv.ui.info.state

import androidx.compose.runtime.Stable
import com.pwssv67.aviv.model.data.EstateInfo

sealed class EstateInfoViewState {
    @Stable
    data class Success(val data: EstateInfo) : EstateInfoViewState()

    @Stable
    data class Error(val message: String?) : EstateInfoViewState()
    object Loading : EstateInfoViewState()
}