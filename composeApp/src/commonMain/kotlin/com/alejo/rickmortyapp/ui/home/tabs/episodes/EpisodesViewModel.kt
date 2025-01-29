package com.alejo.rickmortyapp.ui.home.tabs.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alejo.rickmortyapp.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class EpisodesViewModel(private val repository: Repository) : ViewModel() {

    private val _state = MutableStateFlow<EpisodesState>(EpisodesState())
    val state: StateFlow<EpisodesState> = _state

    init {
        _state.update { episodesState ->
            episodesState.copy(
                episodes = repository.getAllEpisodes().cachedIn(viewModelScope)
            )
        }
    }

    fun onVideoSelected(videoUrl: String) {
        _state.update { state -> state.copy(playVideo = videoUrl) }
    }

    fun onVideoClosed() {
        _state.update { state -> state.copy(playVideo = "") }
    }
}