package com.pwssv67.aviv.data_source.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EstateListDTO(
    @SerialName("items") val estates: List<EstateDTO>,
    @SerialName("totalCount") val total: Int
)