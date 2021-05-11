package com.example.norrisjokes.di

import com.example.norrisjokes.model.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Api{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.icndb.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create()
    }
}