package com.example.norrisjokes.model

import com.example.norrisjokes.data.Joke
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokesRepository @Inject constructor(private val retrofitApi: Api) {


    suspend fun getRandomJokes(jokesAmount: Int): List<Joke>{
        return retrofitApi.getRandomJokes(jokesAmount).value
    }
}