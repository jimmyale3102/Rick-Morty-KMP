package com.alejo.rickmortyapp.ui.home.tabs.episodes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import com.alejo.rickmortyapp.domain.model.EpisodeModel
import com.alejo.rickmortyapp.domain.model.SeasonEpisode
import com.alejo.rickmortyapp.domain.model.SeasonEpisode.*
import com.alejo.rickmortyapp.ui.core.composables.PagingType
import com.alejo.rickmortyapp.ui.core.composables.PagingWrapper
import com.alejo.rickmortyapp.ui.core.composables.RickMortyLoading
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

@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodesScreen() {

    val viewModel = koinViewModel<EpisodesViewModel>()
    val state by viewModel.state.collectAsState()
    val episodes = state.episodes.collectAsLazyPagingItems()

    Box(Modifier.fillMaxSize()) {
        PagingWrapper(
            pagingType = PagingType.ROW,
            pagingItems = episodes,
            initView = { RickMortyLoading() },
            loadingItemsView = { RickMortyLoading() },
        ) { episode ->
            EpisodeItem(episode)
        }
    }
}

@Composable
fun EpisodeItem(episode: EpisodeModel) {
    Box(
        modifier = Modifier
            .width(120.dp)
            .padding(horizontal = 4.dp)
            .clickable {  },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(getSeasonImage(episode.season)),
            contentDescription = episode.name,
            contentScale = ContentScale.Inside
        )
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