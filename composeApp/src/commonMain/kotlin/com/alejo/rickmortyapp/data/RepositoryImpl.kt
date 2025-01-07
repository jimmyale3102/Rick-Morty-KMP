package com.alejo.rickmortyapp.data

import com.alejo.rickmortyapp.data.remote.ApiService
import com.alejo.rickmortyapp.domain.Repository
import com.alejo.rickmortyapp.domain.model.CharacterModel

class RepositoryImpl(private val api: ApiService) : Repository {
    override suspend fun getCharacterById(id: String): CharacterModel {
        return api.getCharacterById(id).toDomain()
    }
}