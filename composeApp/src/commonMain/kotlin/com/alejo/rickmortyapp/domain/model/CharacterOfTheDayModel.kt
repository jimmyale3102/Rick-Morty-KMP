package com.alejo.rickmortyapp.domain.model

import com.alejo.rickmortyapp.data.database.entity.CharacterOfTheDayEntity

data class CharacterOfTheDayModel(
    val character: CharacterModel,
    val selectedDay: String
) {
    fun toEntity(): CharacterOfTheDayEntity = CharacterOfTheDayEntity(
        id = character.id,
        isAlive = character.isAlive,
        image = character.image,
        name = character.name,
        selectedDay = selectedDay
    )
}