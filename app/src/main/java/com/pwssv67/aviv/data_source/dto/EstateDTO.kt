package com.pwssv67.aviv.data_source.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EstateDTO(
    @SerialName("bedrooms") val bedrooms: Int? = null,
    @SerialName("city") val city: String,
    @SerialName("id") val id: Int,
    @SerialName("area") val area: Float? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("price") val price: Float,
    @SerialName("professional") val professional: String,
    @SerialName("propertyType") val propertyType: String,
    @SerialName("offerType") val offerType: Int,
    @SerialName("rooms") val rooms: Int? = null
)