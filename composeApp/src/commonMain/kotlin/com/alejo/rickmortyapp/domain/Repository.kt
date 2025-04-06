package com.alejo.rickmortyapp.domain

import androidx.paging.PagingData
import com.alejo.rickmortyapp.domain.model.CharacterModel
import com.alejo.rickmortyapp.domain.model.CharacterOfTheDayModel
import com.alejo.rickmortyapp.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getCharacterById(id: String): CharacterModel
    fun getAllCharacters() : Flow<PagingData<CharacterModel>>
    fun getAllEpisodes() : Flow<PagingData<EpisodeModel>>
    suspend fun getCharacterOfTheDayDB() : CharacterOfTheDayModel?
    suspend fun saveCharacterEntity(characterOfTheDayModel: CharacterOfTheDayModel)
    suspend fun getCharacterEpisodes(episodes: List<String>): List<EpisodeModel>
}