package com.alejo.rickmortyapp.ui.home.tabs.characters

import com.alejo.rickmortyapp.domain.model.CharacterModel

data class CharacterState(
    val characterOfTheDay: CharacterModel? = null
)