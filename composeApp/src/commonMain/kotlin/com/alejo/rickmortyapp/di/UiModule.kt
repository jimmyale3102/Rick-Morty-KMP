package com.alejo.rickmortyapp.di

import com.alejo.rickmortyapp.ui.character.CharacterDetailsViewModel
import com.alejo.rickmortyapp.ui.home.tabs.characters.CharactersViewModel
import com.alejo.rickmortyapp.ui.home.tabs.episodes.EpisodesViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::CharactersViewModel)
    viewModelOf(::EpisodesViewModel)
    viewModelOf(::CharacterDetailsViewModel)
}