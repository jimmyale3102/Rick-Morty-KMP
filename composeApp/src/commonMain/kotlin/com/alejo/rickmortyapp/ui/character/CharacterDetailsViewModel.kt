package com.alejo.rickmortyapp.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejo.rickmortyapp.domain.Repository
import com.alejo.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailsViewModel(
    character: CharacterModel,
    private val repository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterDetailsState>(CharacterDetailsState(character))
    val uiState: StateFlow<CharacterDetailsState> = _uiState

    init {
        getCharacterEpisodes(character)
    }

    private fun getCharacterEpisodes(character: CharacterModel) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getCharacterEpisodes(character.episodes)
            }
            _uiState.update { state -> state.copy(episodes = result) }
        }
    }

}