package com.pwssv67.aviv.data_source

import com.pwssv67.aviv.data_source.dto.EstateDTO
import com.pwssv67.aviv.data_source.dto.EstateListDTO
import com.pwssv67.aviv.data_source.mappers.toDomain
import com.pwssv67.aviv.data_source.mappers.toDomainWithInfo
import com.pwssv67.aviv.model.data.EstateInfo
import com.pwssv67.aviv.model.data.EstateList
import com.pwssv67.aviv.utils.toResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.path

class EstateDataSourceNetwork(private val client: HttpClient): EstateDataSource {
    override suspend fun getEstates(): Result<EstateList> {
        val response = client.get {
            url {
                path("listings.json")
            }
        }
        return response
            .toResult<EstateListDTO>()
            .mapCatching(EstateListDTO::toDomain)
    }

    override suspend fun getEstateInfo(id: Int): Result<EstateInfo> {
        val response = client.get {
            url {
                path("listings/$id.json")
            }
        }
        return response
            .toResult<EstateDTO>()
            .mapCatching(EstateDTO::toDomainWithInfo)
    }
}