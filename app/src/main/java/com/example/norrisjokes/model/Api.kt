package com.example.norrisjokes.model

import com.example.norrisjokes.data.Joke
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("/jokes/random/{number}")
    suspend fun getRandomJokes(
        @Path("number") jokesAmount: Int
    ): JokesWrapper<Joke>


//    companion object{
//        fun create(): Api{
//            val retrofit = Retrofit.Builder()
//                .baseUrl("https://api.icndb.com")
//                .addConverterFactory(MoshiConverterFactory.create())
//                .build()
//
//            return retrofit.create()
//        }
//    }
}