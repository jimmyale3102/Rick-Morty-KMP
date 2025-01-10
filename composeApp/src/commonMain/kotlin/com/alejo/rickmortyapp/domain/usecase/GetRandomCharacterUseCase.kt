package com.alejo.rickmortyapp.domain.usecase

import com.alejo.rickmortyapp.domain.Repository
import com.alejo.rickmortyapp.domain.model.CharacterModel

class GetRandomCharacterUseCase(private val repository: Repository) {
    suspend operator fun invoke(): CharacterModel {
        val randomCharacterId: Int = (1..826).random()
        return repository.getCharacterById(randomCharacterId.toString())
    }
}