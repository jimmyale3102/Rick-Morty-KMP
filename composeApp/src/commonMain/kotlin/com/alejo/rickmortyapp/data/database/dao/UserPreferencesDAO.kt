package com.alejo.rickmortyapp.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.alejo.rickmortyapp.data.database.entity.CharacterOfTheDayEntity

@Dao
interface UserPreferencesDAO {

    @Query("SELECT * FROM character_of_the_day")
    suspend fun getCharacterOfTheDay(): CharacterOfTheDayEntity?

}