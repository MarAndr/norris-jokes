package com.example.norrisjokes.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.norrisjokes.const.JokesContract
import com.example.norrisjokes.data.Joke

@Dao
interface JokesDao {
    @Query("SELECT * FROM ${JokesContract.TABLE_NAME}")
    suspend fun getJokes(): List<Joke>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJokes(joke: Joke)
}