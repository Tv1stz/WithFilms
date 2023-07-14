package com.example.withfilms.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FilmDao {
    @Upsert
    suspend fun upsertAllFilms(films: List<FilmEntity>)

    @Query("SELECT * FROM paging_films")
    fun pagingSourceFilms(): PagingSource<Int, FilmEntity>

    @Query("DELETE FROM paging_films")
    suspend fun clearAllFilms()

}