package com.example.norrisjokes.repository.network

import com.example.norrisjokes.data.Joke
import com.example.norrisjokes.data.JokesWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("/jokes/random/{number}")
    suspend fun getRandomJokes(
        @Path("number") jokesAmount: Int
    ): JokesWrapper<Joke>
}