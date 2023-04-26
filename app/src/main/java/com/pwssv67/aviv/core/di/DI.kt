package com.pwssv67.aviv.core.di

import org.koin.core.module.Module

//root of koin di
val rootDIModule:List<Module> = listOf(
    NetworkDIModule,
    EstateDIModule
)