package com.alejo.rickmortyapp.ui.home.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.alejo.rickmortyapp.domain.model.CharacterModel
import com.alejo.rickmortyapp.ui.core.BackgroundPrimaryColor
import com.alejo.rickmortyapp.ui.core.DefaultTextColor
import com.alejo.rickmortyapp.ui.core.Green
import com.alejo.rickmortyapp.ui.core.composables.PagingType
import com.alejo.rickmortyapp.ui.core.composables.PagingWrapper
import com.alejo.rickmortyapp.ui.core.ext.vertical
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen(navigateToCharacterDetails: (CharacterModel) -> Unit) {
    val viewModel = koinViewModel<CharactersViewModel>()
    val state by viewModel.state.collectAsState()
    val characters = state.characters.collectAsLazyPagingItems()
    CharacterGridList(characters = characters, state = state) { character ->
        navigateToCharacterDetails(character)
    }
}

@Composable
fun CharacterGridList(
    characters: LazyPagingItems<CharacterModel>,
    state: CharacterState,
    onCharacterSelected: (CharacterModel) -> Unit
) {
    PagingWrapper(
        modifier = Modifier.fillMaxSize()
            .background(BackgroundPrimaryColor)
            .padding(horizontal = 16.dp),
        pagingType = PagingType.VERTICAL_GRID,
        pagingItems = characters,
        emptyView = {
            Text(text = "No characters found")
        },
        initView = {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        },
        loadingItemsView = {
            Box(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        contentHeader = {
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Characters",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = DefaultTextColor
                )
                CharacterOfTheDay(character = state.characterOfTheDay)
            }
        }
    ) { character ->
        CharacterCard(character = character) { characterModel ->
            onCharacterSelected(characterModel)
        }
    }
}

@Composable
fun CharacterCard(character: CharacterModel, onCharacterSelected: (CharacterModel) -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24))
            .border(2.dp, Green, RoundedCornerShape(0, 24, 24, 24))
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onCharacterSelected(character) },
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
        Box(Modifier.fillMaxSize().background(Green.copy(alpha = 0.3f)))
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