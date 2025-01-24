package com.alejo.rickmortyapp.domain

import androidx.paging.PagingData
import com.alejo.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getCharacterById(id: String): CharacterModel
    fun getAllCharacters() : Flow<PagingData<CharacterModel>>
    suspend fun getCharacterOfTheDayDB()
}