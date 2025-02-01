package com.alejo.rickmortyapp.ui.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.alejo.rickmortyapp.domain.model.CharacterModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parameterSetOf
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.Rick_and_Morty_season_2
import rickmortyapp.composeapp.generated.resources.space

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharacterDetailsScreen(character: CharacterModel) {
    val viewModel = koinViewModel<CharacterDetailsViewModel>{ parameterSetOf(character) }
    val state = viewModel.uiState.collectAsState()

    Column(Modifier.fillMaxSize().background(Color.White)) {
        MainHeader(character)
    }
}

@Composable
private fun MainHeader(character: CharacterModel) {
    Box(Modifier.fillMaxWidth().height(300.dp)) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(Res.drawable.space),
            contentScale = ContentScale.Crop,
            contentDescription = "Background header"
        )
        CharacterHeader(character)
    }
}

@Composable
private fun CharacterHeader(character: CharacterModel) {

}
