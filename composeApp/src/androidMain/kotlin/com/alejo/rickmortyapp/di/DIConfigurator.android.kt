package com.alejo.rickmortyapp.di

import com.alejo.rickmortyapp.data.database.RickMortyDatabase
import com.alejo.rickmortyapp.data.database.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single <RickMortyDatabase>{ getDatabase(get()) }
}