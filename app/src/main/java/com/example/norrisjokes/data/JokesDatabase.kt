package com.example.norrisjokes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.norrisjokes.model.JokesDao

@Database(entities = [Joke::class], version = JokesDatabase.DB_VERSION)
abstract class JokesDatabase: RoomDatabase() {
    companion object{
        const val DB_NAME = "jokes_db"
        const val DB_VERSION = 1
    }

    abstract fun jokesDao(): JokesDao
}