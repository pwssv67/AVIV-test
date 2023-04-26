package com.pwssv67.aviv.ui.list.state

import androidx.compose.runtime.Stable
import com.pwssv67.aviv.model.data.EstateList


sealed class EstateListViewState {
    object Loading : EstateListViewState()

    @Stable
    data class Error(val message: String?) : EstateListViewState()

    @Stable
    data class Success(val data: EstateList) : EstateListViewState()
}