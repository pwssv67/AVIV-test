package com.pwssv67.aviv.core.di

import com.pwssv67.aviv.data_source.EstateDataSource
import com.pwssv67.aviv.data_source.EstateDataSourceNetwork
import com.pwssv67.aviv.model.repository.EstateRepository
import com.pwssv67.aviv.model.repository.EstateRepositoryImpl
import com.pwssv67.aviv.ui.info.viewmodel.EstateInfoViewModel
import com.pwssv67.aviv.ui.list.viewmodel.EstateListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val EstateDIModule = module {
    single<EstateDataSource> { EstateDataSourceNetwork(get()) }
    single<EstateRepository> { EstateRepositoryImpl(get()) }
    viewModel { EstateListViewModel(get()) }
    viewModel { params -> EstateInfoViewModel(params.get(), get()) }
}