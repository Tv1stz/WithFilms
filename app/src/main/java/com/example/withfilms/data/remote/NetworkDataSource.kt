package com.example.withfilms.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val filmService: FilmService
) {
    fun getTopFilms() = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = {
            FilmPagingSource(filmService)
        }
    ).flow

    suspend fun getFilmDetailById(filmId: Int) =
        filmService.getFilmDetailById(filmId = filmId)

    suspend fun getFilmStaffById(filmId: Int) =
        filmService.getFilmStaffById(filmId = filmId)

    suspend fun getSearchFilmByKeyWord(searchQuery: String) =
        filmService.getSearchFilmByKeyWord(keyWord = searchQuery)

    suspend fun getPersonDetailById(personId: Int) =
        filmService.getPersonDetailById(actorId = personId)
}