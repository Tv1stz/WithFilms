package com.example.withfilms.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.withfilms.data.remote.FilmPagingSource
import com.example.withfilms.data.remote.FilmService
import com.example.withfilms.data.remote.model.films.Film
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val filmService: FilmService
) {

    fun getTopFilm(): Flow<PagingData<Film>> {
        return Pager(PagingConfig(pageSize = 20)) {
            FilmPagingSource(filmService)
        }.flow
    }

    suspend fun getFilmDetailById(id: Long) =
        filmService.getFilmDetailById(id = id)

    suspend fun getFilmStaffById(filmId: Long) =
        filmService.getFilmStaffById(filmId = filmId)
}