package com.alejo.rickmortyapp.ui.home.tabs.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen() {
    val viewModel = koinViewModel<CharactersViewModel>()
    val state by viewModel.state.collectAsState()

    Column(Modifier.fillMaxSize()) {
        state.characterOfTheDay?.let {
            Text(it.image)
        }
    }
}