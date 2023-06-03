package com.example.withfilms.data

import com.example.withfilms.data.remote.FilmService
import javax.inject.Inject

class Repository @Inject constructor(
    private val filmService: FilmService
) {
    suspend fun getTopFilms() = filmService.getTopFilms()

    suspend fun getFilmDetailById(id: Long) =
        filmService.getFilmDetailById(id = id)

    suspend fun getFilmStaffById(filmId: Long) =
        filmService.getFilmStaffById(filmId = filmId)
}