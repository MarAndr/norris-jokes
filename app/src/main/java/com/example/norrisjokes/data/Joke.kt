package com.example.norrisjokes.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.norrisjokes.const.JokesContract

@Entity(tableName = JokesContract.TABLE_NAME)
data class Joke(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = JokesContract.Columns.ID)
    val id: Long = 0,
    @ColumnInfo(name = JokesContract.Columns.TEXT)
    val text: String
)