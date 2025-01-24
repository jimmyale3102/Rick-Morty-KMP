package com.alejo.rickmortyapp.domain.usecase

import com.alejo.rickmortyapp.domain.Repository
import com.alejo.rickmortyapp.domain.model.CharacterModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetRandomCharacterUseCase(private val repository: Repository) {
    suspend operator fun invoke(): CharacterModel {

        /*
        val characterSaved = repository.savedCharacter()
        if (characterSaved != null) {
            if (characterSaved.date == getCurrentDayOfTheYear()) {
                return characterSaved
            }
        } else {


        }
         */
        repository.getCharacterOfTheDayDB()
        val randomCharacterId: Int = (1..826).random()
        return repository.getCharacterById(randomCharacterId.toString())
    }

    private fun getCurrentDayOfTheYear(): String {
        val instant: Instant = Clock.System.now()
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localDateTime.dayOfYear}${localDateTime.year}"
    }
}