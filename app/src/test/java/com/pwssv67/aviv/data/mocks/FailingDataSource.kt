package com.pwssv67.aviv.data.mocks

import com.pwssv67.aviv.data_source.EstateDataSource
import com.pwssv67.aviv.model.data.EstateInfo
import com.pwssv67.aviv.model.data.EstateList

class FailingDataSource() : EstateDataSource {
    override suspend fun getEstates(): Result<EstateList> {
        return Result.failure(Exception(errorFetchingEstatesMessage))
    }

    override suspend fun getEstateInfo(id: Int): Result<EstateInfo> {
        return Result.failure(Exception(errorFetchingInfoMessage))
    }

    companion object {
        const val errorFetchingEstatesMessage = "Error fetching estates"
        const val errorFetchingInfoMessage = "Error fetching estate info"
    }
}