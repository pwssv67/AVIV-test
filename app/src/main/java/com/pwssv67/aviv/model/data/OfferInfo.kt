package com.pwssv67.aviv.model.data

import androidx.compose.runtime.Stable

@Stable
data class OfferInfo(
    val price: Float,
    val offerType: OfferType,
    val professional: String
)