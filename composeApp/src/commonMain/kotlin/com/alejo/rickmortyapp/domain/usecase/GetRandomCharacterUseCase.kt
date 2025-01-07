package com.alejo.rickmortyapp.domain.usecase

import com.alejo.rickmortyapp.domain.Repository

class GetRandomCharacterUseCase(private val repository: Repository) {
    suspend operator fun invoke() {
        val randomCharacterId: Int = (1..826).random()
        repository.getCharacterById(randomCharacterId.toString())
    }
}