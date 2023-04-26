package com.pwssv67.aviv.model.repository

import com.pwssv67.aviv.data_source.EstateDataSource
import com.pwssv67.aviv.model.data.EstateInfo
import com.pwssv67.aviv.model.data.EstateList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EstateRepositoryImpl(
    private val estateDataSource: EstateDataSource
): EstateRepository {
    override fun getEstates(): Flow<Result<EstateList>> {
        return flow {
            val result = estateDataSource.getEstates()
            emit(result)
        }
    }

    override fun getEstateInfo(id: Int): Flow<Result<EstateInfo>> {
        return flow {
            val result = estateDataSource.getEstateInfo(id)
            emit(result)
        }
    }
}