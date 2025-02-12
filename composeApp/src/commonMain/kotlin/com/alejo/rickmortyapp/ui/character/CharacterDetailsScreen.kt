package com.alejo.rickmortyapp.ui.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.alejo.rickmortyapp.domain.model.CharacterModel
import com.alejo.rickmortyapp.domain.model.EpisodeModel
import com.alejo.rickmortyapp.ui.core.BackgroundPrimaryColor
import com.alejo.rickmortyapp.ui.core.BackgroundSecondaryColor
import com.alejo.rickmortyapp.ui.core.BackgroundTertiaryColor
import com.alejo.rickmortyapp.ui.core.DefaultTextColor
import com.alejo.rickmortyapp.ui.core.Green
import com.alejo.rickmortyapp.ui.core.Pink
import com.alejo.rickmortyapp.ui.core.ext.aliveBorder
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parameterSetOf
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.space

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharacterDetailsScreen(character: CharacterModel) {
    val viewModel = koinViewModel<CharacterDetailsViewModel> { parameterSetOf(character) }
    val state by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Column(Modifier.fillMaxSize().background(BackgroundPrimaryColor).verticalScroll(scrollState)) {
        MainHeader(state.character)
        Spacer(Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxSize()
                .clip(RoundedCornerShape(topStartPercent = 6, topEndPercent = 6))
                .background(BackgroundSecondaryColor)
        ) {
            CharacterInformation(state.character)
            CharacterEpisodes(state.episodes)
        }
    }
}

@Composable
fun CharacterInformation(character: CharacterModel) {
    ElevatedCard(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = BackgroundTertiaryColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("About the character", color = DefaultTextColor)
            Spacer(Modifier.height(4.dp))
            InformationDetails(title = "Origin: ", detail = character.origin)
            Spacer(Modifier.height(2.dp))
            InformationDetails(title = "Gender: ", detail = character.gender)
        }
    }
}

@Composable
fun InformationDetails(title: String, detail: String) {
    Row {
        Text(text = title, color = Black, fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(4.dp))
        Text(text = detail, color = Green)
    }
}

@Composable
fun CharacterEpisodes(episodes: List<EpisodeModel>?) {
    ElevatedCard(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = BackgroundTertiaryColor)
    ) {
        Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
            if (episodes == null) {
                CircularProgressIndicator(color = Green)
            } else {
                Column(Modifier.fillMaxWidth()) {
                    Text("Character episodes", color = DefaultTextColor)
                    Spacer(Modifier.height(6.dp))

                    episodes.forEach { episode ->
                        EpisodeItem(episode)
                        Spacer(Modifier.height(4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun EpisodeItem(episode: EpisodeModel) {
    Text(episode.name, color = Green, fontWeight = FontWeight.Bold)
    Text(episode.episode, color = DefaultTextColor)
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
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(topStartPercent = 12, topEndPercent = 12))
                .background(White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = character.name,
                color = Pink,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Species: ${character.species}", color = Black)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.padding(top = 16.dp))
            Box(contentAlignment = Alignment.TopCenter) {
                Box(
                    modifier = Modifier.size(204.dp).clip(CircleShape).background(
                        brush = Brush.radialGradient(
                            0f to Black,
                            0.8f to Black,
                            1f to Color.Transparent
                        )
                    )
                ) {
                    AsyncImage(
                        model = character.image,
                        contentDescription = "Character image",
                        modifier = Modifier.size(190.dp)
                            .clip(CircleShape)
                            .align(Alignment.Center)
                            .aliveBorder(character.isAlive),
                        contentScale = ContentScale.Crop
                    )
                }
                val aliveCopy = if (character.isAlive) "Alive" else "Dead"
                val aliveBackgroundColor = if (character.isAlive) Green else Red
                val aliveTextColor = if (character.isAlive) Black else White
                Text(
                    text = aliveCopy,
                    color = aliveTextColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clip(RoundedCornerShape(30))
                        .background(aliveBackgroundColor)
                        .padding(vertical = 2.dp, horizontal = 6.dp)
                )
            }
            Spacer(Modifier.weight(1f))
        }
    }
}
