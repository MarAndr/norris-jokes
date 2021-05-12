package com.example.norrisjokes.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class JokesWrapper<T>(
    val value: List<T>
)