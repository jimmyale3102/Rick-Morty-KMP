package com.alejo.rickmortyapp.ui.home.tabs.episodes

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import com.alejo.rickmortyapp.domain.model.EpisodeModel
import com.alejo.rickmortyapp.domain.model.SeasonEpisode
import com.alejo.rickmortyapp.domain.model.SeasonEpisode.SEASON_1
import com.alejo.rickmortyapp.domain.model.SeasonEpisode.SEASON_2
import com.alejo.rickmortyapp.domain.model.SeasonEpisode.SEASON_3
import com.alejo.rickmortyapp.domain.model.SeasonEpisode.SEASON_4
import com.alejo.rickmortyapp.domain.model.SeasonEpisode.SEASON_5
import com.alejo.rickmortyapp.domain.model.SeasonEpisode.SEASON_6
import com.alejo.rickmortyapp.domain.model.SeasonEpisode.SEASON_7
import com.alejo.rickmortyapp.domain.model.SeasonEpisode.UNKNOWN
import com.alejo.rickmortyapp.ui.core.BackgroundPrimaryColor
import com.alejo.rickmortyapp.ui.core.BackgroundSecondaryColor
import com.alejo.rickmortyapp.ui.core.BackgroundTertiaryColor
import com.alejo.rickmortyapp.ui.core.DefaultTextColor
import com.alejo.rickmortyapp.ui.core.PlaceholderColor
import com.alejo.rickmortyapp.ui.core.composables.PagingType
import com.alejo.rickmortyapp.ui.core.composables.PagingWrapper
import com.alejo.rickmortyapp.ui.core.composables.RickMortyLoading
import com.alejo.rickmortyapp.ui.core.composables.VideoPlayer
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.Rick_and_Morty_season_1
import rickmortyapp.composeapp.generated.resources.Rick_and_Morty_season_2
import rickmortyapp.composeapp.generated.resources.Rick_and_Morty_season_3
import rickmortyapp.composeapp.generated.resources.Rick_and_Morty_season_4
import rickmortyapp.composeapp.generated.resources.Rick_and_Morty_season_5
import rickmortyapp.composeapp.generated.resources.Rick_and_Morty_season_6
import rickmortyapp.composeapp.generated.resources.Rick_and_Morty_season_7
import rickmortyapp.composeapp.generated.resources.video_placeholder

@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodesScreen() {

    val viewModel = koinViewModel<EpisodesViewModel>()
    val state by viewModel.state.collectAsState()
    val episodes = state.episodes.collectAsLazyPagingItems()

    Column(Modifier.fillMaxSize().background(BackgroundPrimaryColor).padding(top = 16.dp)) {
        Spacer(Modifier.height(16.dp))
        PagingWrapper(
            pagingType = PagingType.ROW,
            pagingItems = episodes,
            initView = { RickMortyLoading() },
            loadingItemsView = { RickMortyLoading() },
        ) { episode ->
            EpisodeItem(episode) { videoUrl ->
                viewModel.onVideoSelected(videoUrl)
            }
        }
        AnimatedContent(state.playVideo.isNotBlank()) { condition ->
            if (condition) {
                VideoComponent(state.playVideo) { viewModel.onVideoClosed() }
            } else {
                VideoPlaceholder()
            }
        }
    }
}

@Composable
fun VideoPlaceholder() {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .height(250.dp)
            .border(2.dp, Color.Green, CardDefaults.elevatedShape),
        colors = CardDefaults.elevatedCardColors(containerColor = PlaceholderColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Res.drawable.video_placeholder),
                contentDescription = null
            )
            Text(
                text = "Aw, jeez, you gotta click the vide, guys! I mean, it might be important or something!",
                color = DefaultTextColor,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
private fun VideoComponent(videoUrl: String, onVideoClosed: () -> Unit) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .height(250.dp)
            .border(2.dp, Color.Green, CardDefaults.elevatedShape)
    ) {
        Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
            Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                VideoPlayer(
                    modifier = Modifier.fillMaxWidth(),
                    url = videoUrl
                )
            }
            Row(Modifier.fillMaxWidth().padding(8.dp)) {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier.clickable { onVideoClosed() },
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }
    }
}

@Composable
fun EpisodeItem(episode: EpisodeModel, onEpisodeSelected: (String) -> Unit) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .padding(horizontal = 4.dp)
            .clickable { onEpisodeSelected(episode.videoURL) }
            .clip(RoundedCornerShape(8)),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(getSeasonImage(episode.season)),
                contentDescription = episode.name,
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(2.dp))
            Text(text = episode.episode, color = DefaultTextColor, fontWeight = FontWeight.Bold)
        }
    }
}

private fun getSeasonImage(season: SeasonEpisode): DrawableResource {
    return when (season) {
        SEASON_1 -> Res.drawable.Rick_and_Morty_season_1
        SEASON_2 -> Res.drawable.Rick_and_Morty_season_2
        SEASON_3 -> Res.drawable.Rick_and_Morty_season_3
        SEASON_4 -> Res.drawable.Rick_and_Morty_season_4
        SEASON_5 -> Res.drawable.Rick_and_Morty_season_5
        SEASON_6 -> Res.drawable.Rick_and_Morty_season_6
        SEASON_7 -> Res.drawable.Rick_and_Morty_season_7
        UNKNOWN -> Res.drawable.Rick_and_Morty_season_1
    }
}