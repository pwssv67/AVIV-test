package com.pwssv67.aviv.model.data

import androidx.compose.runtime.Stable

@Stable
data class Estate(
    val id: Int,
    val imgUrl: String?,
    val offerInfo: OfferInfo,
    val propertyInfo: PropertyInfo,
    val city: String
)

