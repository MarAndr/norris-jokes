package com.example.norrisjokes.repository

import com.example.norrisjokes.data.Joke
import com.example.norrisjokes.repository.network.Api
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokesRepository @Inject constructor(private val retrofitApi: Api) {


    suspend fun getRandomJokes(jokesAmount: Int): List<Joke>{
        return retrofitApi.getRandomJokes(jokesAmount).value
    }
}