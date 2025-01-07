package com.alejo.rickmortyapp.domain

import com.alejo.rickmortyapp.domain.model.CharacterModel

interface Repository {
    suspend fun getCharacterById(id: String): CharacterModel
}