package com.example.norrisjokes.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Joke(
    val id: Long = 0,
    @Json(name = "joke")
    val text: String
)