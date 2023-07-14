package com.example.withfilms.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.withfilms.data.FilmRemoteMediator
import com.example.withfilms.data.db.FilmDatabase
import com.example.withfilms.data.db.FilmEntity
import com.example.withfilms.data.remote.FilmService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object FilmPagerModule {
    @Provides
    @Singleton
    fun provideFilmPager(filmDatabase: FilmDatabase, filmService: FilmService): Pager<Int, FilmEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = FilmRemoteMediator(
                filmDatabase = filmDatabase,
                filmService = filmService
            ),
            pagingSourceFactory = {
                filmDatabase.dao.pagingSourceFilms()
            }
        )
    }
}