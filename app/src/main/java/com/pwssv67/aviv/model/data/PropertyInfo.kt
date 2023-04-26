package com.pwssv67.aviv.model.data

import androidx.compose.runtime.Stable

@Stable
data class PropertyInfo(
    val bedrooms: Int?,
    val rooms: Int?,
    val area: Float?,
    val propertyType: String
)