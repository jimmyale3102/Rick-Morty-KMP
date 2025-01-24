package com.alejo.rickmortyapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_of_the_day")
class CharacterOfTheDayEntity(
    @PrimaryKey
    val id: String,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val dateSelected: String
)