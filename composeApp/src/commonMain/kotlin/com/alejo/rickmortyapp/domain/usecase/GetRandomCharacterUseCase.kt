package com.alejo.rickmortyapp.domain.usecase

import com.alejo.rickmortyapp.domain.Repository
import com.alejo.rickmortyapp.domain.model.CharacterModel
import com.alejo.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetRandomCharacterUseCase(private val repository: Repository) {
    suspend operator fun invoke(): CharacterModel {
        val characterEntity = repository.getCharacterOfTheDayDB()
        val currentDay = getCurrentDayOfTheYear()
        return if (characterEntity != null && characterEntity.selectedDay == currentDay) {
            characterEntity.character
        } else {
            val character = getRandomCharacter(repository)
            repository.saveCharacterEntity(
                CharacterOfTheDayModel(
                    character = character,
                    selectedDay = currentDay
                )
            )
            character
        }
    }

    private suspend fun getRandomCharacter(repository: Repository): CharacterModel {
        val randomCharacterId: Int = (1..826).random()
        return repository.getCharacterById(randomCharacterId.toString())
    }

    private fun getCurrentDayOfTheYear(): String {
        val instant: Instant = Clock.System.now()
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localDateTime.dayOfYear}${localDateTime.year}"
    }
}