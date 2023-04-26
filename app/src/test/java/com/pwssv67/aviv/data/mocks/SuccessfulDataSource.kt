package com.pwssv67.aviv.data.mocks

import com.pwssv67.aviv.data_source.EstateDataSource
import com.pwssv67.aviv.model.data.EstateInfo
import com.pwssv67.aviv.model.data.EstateList
import com.pwssv67.aviv.utils.preview.EstateGenerator

class SuccessfulDataSource() : EstateDataSource {
    override suspend fun getEstates(): Result<EstateList> {
        return Result.success(
            EstateGenerator.getEstateList()
        )
    }

    override suspend fun getEstateInfo(id: Int): Result<EstateInfo> {
        return Result.success(EstateGenerator.getEstateInfo(id))
    }
}