package com.alejo.rickmortyapp.domain.model

import com.alejo.rickmortyapp.data.database.entity.CharacterOfTheDayEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class CharacterOfTheDayModel(
    val character: CharacterModel,
    val selectedDay: String
) {
    fun toEntity(): CharacterOfTheDayEntity = CharacterOfTheDayEntity(
        id = character.id,
        isAlive = character.isAlive,
        image = character.image,
        name = character.name,
        selectedDay = selectedDay,
        species = character.species,
        gender = character.gender,
        origin = character.origin,
        episodes = Json.encodeToString(character.episodes)
    )
}