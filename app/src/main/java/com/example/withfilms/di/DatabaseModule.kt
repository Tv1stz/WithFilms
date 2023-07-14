package com.example.withfilms.di

import android.content.Context
import androidx.room.Room
import com.example.withfilms.data.db.FilmDao
import com.example.withfilms.data.db.FilmDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : FilmDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            FilmDatabase::class.java,
            "Film.db",

            )
            .build()
    }

    @Provides
    fun provideFilmDao(database: FilmDatabase): FilmDao = database.dao
}