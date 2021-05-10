package com.example.norrisjokes.model

import com.example.norrisjokes.data.Joke
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokesRepository @Inject constructor(private val jokesDao: JokesDao, private val retrofitApi: Api) {

    suspend fun addJoke(joke: Joke){
        jokesDao.addJokes(joke)
    }

    suspend fun getJokes(): List<Joke>{
        return jokesDao.getJokes()
    }

    suspend fun getRandomJokes(jokesAmount: Int): List<Joke>{
        return retrofitApi.getRandomJokes(jokesAmount).value
    }
}