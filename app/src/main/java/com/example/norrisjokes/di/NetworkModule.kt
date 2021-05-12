package com.example.norrisjokes.di

import com.example.norrisjokes.repository.network.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Api {

        val httpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.icndb.com")
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create()
    }
}