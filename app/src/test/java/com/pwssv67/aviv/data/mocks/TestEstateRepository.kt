package com.pwssv67.aviv.data.mocks

import com.pwssv67.aviv.model.data.EstateInfo
import com.pwssv67.aviv.model.data.EstateList
import com.pwssv67.aviv.model.repository.EstateRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class TestEstateRepository() : EstateRepository {
    private val estateListFlow = MutableSharedFlow<Result<EstateList>>(replay = 1)
    private val estateInfoFlow = MutableSharedFlow<Result<EstateInfo>>(replay = 1)
    override fun getEstateInfo(id: Int): Flow<Result<EstateInfo>> = estateInfoFlow.asSharedFlow()
    override fun getEstates(): Flow<Result<EstateList>> = estateListFlow.asSharedFlow()

    fun emitEstateList(estateList: EstateList) {
        estateListFlow.tryEmit(Result.success(estateList))
    }

    fun emitEstateInfo(estateInfo: EstateInfo) {
        estateInfoFlow.tryEmit(Result.success(estateInfo))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun resetCaches() {
        estateListFlow.resetReplayCache()
        estateInfoFlow.resetReplayCache()
    }

    fun emitErrors() {
        estateListFlow.tryEmit(Result.failure(Exception(ERROR_FETCHING_ESTATE_LIST)))
        estateInfoFlow.tryEmit(Result.failure(Exception(ERROR_FETCHING_ESTATE_INFO)))
    }

    companion object {
        const val ERROR_FETCHING_ESTATE_LIST = "Error fetching estates"
        const val ERROR_FETCHING_ESTATE_INFO = "Error fetching estate info"
    }
}