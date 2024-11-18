package com.alejo.rickmortyapp.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

expect fun platformModule(): Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        modules(
            uiModule,
            dataModule,
            domainModule,
            platformModule()
        )
    }
}