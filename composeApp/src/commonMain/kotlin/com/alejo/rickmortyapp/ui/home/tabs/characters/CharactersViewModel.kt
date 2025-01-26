package com.alejo.rickmortyapp.ui.home.tabs.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alejo.rickmortyapp.domain.Repository
import com.alejo.rickmortyapp.domain.usecase.GetRandomCharacterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel(
    private val getRandomCharacterUseCase: GetRandomCharacterUseCase,
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow<CharacterState>(CharacterState())
    val state: StateFlow<CharacterState> = _state

    init {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getRandomCharacterUseCase()
            }
            _state.update { state -> state.copy(characterOfTheDay = result) }
        }
        getAllCharacters()
    }

    private fun getAllCharacters() {
        _state.update { state ->
            state.copy(
                characters = repository.getAllCharacters().cachedIn(viewModelScope)
            )
        }
    }
}