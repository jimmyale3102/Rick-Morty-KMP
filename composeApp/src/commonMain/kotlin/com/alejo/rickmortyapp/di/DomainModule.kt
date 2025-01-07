package com.alejo.rickmortyapp.di

import com.alejo.rickmortyapp.domain.usecase.GetRandomCharacterUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetRandomCharacterUseCase)
}
