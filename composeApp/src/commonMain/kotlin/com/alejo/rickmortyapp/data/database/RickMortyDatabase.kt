package com.alejo.rickmortyapp.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.alejo.rickmortyapp.data.database.dao.UserPreferencesDAO
import com.alejo.rickmortyapp.data.database.entity.CharacterOfTheDayEntity

const val DATABASE_NAME = "rick_morty_database.db"

expect object RickMortyCTor: RoomDatabaseConstructor<RickMortyDatabase>

@Database(entities = [CharacterOfTheDayEntity::class], version = 1)
@ConstructedBy(RickMortyCTor::class)
abstract class RickMortyDatabase : RoomDatabase() {
    abstract fun getPreferencesDAO(): UserPreferencesDAO
}