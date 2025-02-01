package com.alejo.rickmortyapp.ui.character

import androidx.lifecycle.ViewModel
import com.alejo.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterDetailsViewModel(character: CharacterModel) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterDetailsState>(CharacterDetailsState(character))
    val uiState: StateFlow<CharacterDetailsState> = _uiState

}