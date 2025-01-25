package com.alejo.rickmortyapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alejo.rickmortyapp.domain.model.CharacterModel
import com.alejo.rickmortyapp.domain.model.CharacterOfTheDayModel

@Entity(tableName = "character_of_the_day")
class CharacterOfTheDayEntity(
    @PrimaryKey
    val id: String,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val selectedDay: String
) {
    fun toDomain(): CharacterOfTheDayModel = CharacterOfTheDayModel(
        character = CharacterModel(
            id = id,
            isAlive = isAlive,
            image = image,
            name = name
        ),
        selectedDay = selectedDay
    )
}