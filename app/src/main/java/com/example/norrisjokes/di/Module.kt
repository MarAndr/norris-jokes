package com.example.norrisjokes.di

import android.app.Application
import androidx.room.Room
import com.example.norrisjokes.data.JokesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object Module {

    @Singleton
    @Provides
    fun providesDatabase(application: Application) = Room.databaseBuilder(
        application,
        JokesDatabase::class.java,
        JokesDatabase.DB_NAME
    ).build()

    @Provides
    fun providesJokesDao(db: JokesDatabase) = db.jokesDao()
}