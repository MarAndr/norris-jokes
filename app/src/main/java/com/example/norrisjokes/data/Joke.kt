package com.example.norrisjokes.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.norrisjokes.const.JokesContract
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = JokesContract.TABLE_NAME)
data class Joke(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = JokesContract.Columns.ID)
    val id: Long = 0,
    @ColumnInfo(name = JokesContract.Columns.TEXT)
    @Json(name = "joke")
    val text: String
)