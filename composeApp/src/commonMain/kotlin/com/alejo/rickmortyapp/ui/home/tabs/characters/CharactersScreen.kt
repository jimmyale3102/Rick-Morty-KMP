package com.alejo.rickmortyapp.ui.home.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.alejo.rickmortyapp.domain.model.CharacterModel
import com.alejo.rickmortyapp.ui.core.ext.vertical
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen() {
    val viewModel = koinViewModel<CharactersViewModel>()
    val state by viewModel.state.collectAsState()

    Column(Modifier.fillMaxSize()) {
        CharacterOfTheDay(character = state.characterOfTheDay)
    }
}

@Composable
fun CharacterOfTheDay(character: CharacterModel? = null) {
    Card(Modifier.fillMaxWidth().height(400.dp), shape = RoundedCornerShape(12)) {
        if(character == null) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            CharacterImage(character = character)
        }
    }
}

@Composable
fun CharacterImage(character: CharacterModel) {
    Box(contentAlignment = Alignment.BottomStart) {
        Box(Modifier.fillMaxSize().background(Color.Green.copy(alpha = 0.3f)))
        AsyncImage(
            model = character.image,
            contentDescription = "Character of the day",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            Modifier.fillMaxSize().background(
                Brush.horizontalGradient(
                    0f to Color.Black.copy(alpha = 0.9f),
                    0.4f to Color.White.copy(alpha = 0f)
                )
            )
        )
        Text(
            text = "Jimmy",
            fontSize = 40.sp,
            maxLines = 1,
            minLines = 1,
            textAlign = TextAlign.Center,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .vertical()
                .rotate(-90f)
        )
    }
}