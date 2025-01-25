package com.alejo.rickmortyapp.ui.home.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
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
    val characters = state.characters.collectAsLazyPagingItems()
    CharacterGridList(characters = characters, state = state)
}

@Composable
fun CharacterGridList(characters: LazyPagingItems<CharacterModel>, state: CharacterState) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            CharacterOfTheDay(character = state.characterOfTheDay)
        }

        when {
            characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
                item(span = { GridItemSpan(2) }) {
                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }

            characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
                item(span = { GridItemSpan(2) }) {
                    Text(text = "No characters found")
                }
            }

            else -> {
                items(characters.itemCount) { characterModel ->
                    characters[characterModel]?.let { character ->
                        CharacterCard(character = character)
                    }
                }

                if (characters.loadState.append is LoadState.Loading) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(100.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun CharacterCard(character: CharacterModel) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24))
            .border(2.dp, Color.Green, RoundedCornerShape(0, 24, 24, 24))
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = "Character image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            Modifier.fillMaxWidth().height(56.dp).background(
                Brush.verticalGradient(
                    listOf(
                        Color.Black.copy(alpha = 0f),
                        Color.Black.copy(alpha = 0.5f),
                        Color.Black.copy(alpha = 1f)
                    )
                )
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = character.name,
                color = Color.White,
                fontSize = 18.sp,
                maxLines = 1,
                minLines = 1
            )
        }
    }
}

@Composable
fun CharacterOfTheDay(character: CharacterModel? = null) {
    Card(Modifier.fillMaxWidth().height(400.dp), shape = RoundedCornerShape(12)) {
        if (character == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
            text = character.name,
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
                .padding(horizontal = 16.dp)
        )
    }
}