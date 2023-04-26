package com.pwssv67.aviv.model.data

import androidx.compose.runtime.Stable

@Stable
/**
 * For now it only encapsulates an [Estate] object, but it can be extended to include more information
 */
data class EstateInfo(
    val estate: Estate,
)