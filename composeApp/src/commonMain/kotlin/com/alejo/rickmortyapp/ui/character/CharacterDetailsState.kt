package com.alejo.rickmortyapp.ui.character

import com.alejo.rickmortyapp.domain.model.CharacterModel
import com.alejo.rickmortyapp.domain.model.EpisodeModel

data class CharacterDetailsState(
    val character: CharacterModel,
    val episodes: List<EpisodeModel>? = null
)