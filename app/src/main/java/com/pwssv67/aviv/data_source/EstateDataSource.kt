package com.pwssv67.aviv.data_source

import com.pwssv67.aviv.model.data.EstateInfo
import com.pwssv67.aviv.model.data.EstateList


interface EstateDataSource {
    suspend fun getEstates(): Result<EstateList>

    suspend fun getEstateInfo(id: Int): Result<EstateInfo>
}