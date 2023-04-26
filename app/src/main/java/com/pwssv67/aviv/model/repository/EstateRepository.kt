package com.pwssv67.aviv.model.repository

import com.pwssv67.aviv.model.data.EstateInfo
import com.pwssv67.aviv.model.data.EstateList
import kotlinx.coroutines.flow.Flow

interface EstateRepository {
    fun getEstates(): Flow<Result<EstateList>>
    fun getEstateInfo(id: Int): Flow<Result<EstateInfo>>
}