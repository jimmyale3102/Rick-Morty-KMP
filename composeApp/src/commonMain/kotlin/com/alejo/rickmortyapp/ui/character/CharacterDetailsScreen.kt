package com.alejo.rickmortyapp.ui.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.alejo.rickmortyapp.domain.model.CharacterModel
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
    val state = viewModel.uiState.collectAsState()

    Column(Modifier.fillMaxSize().background(White)) {
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
                color = Black,
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
