package com.example.withfilms.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.example.withfilms.data.remote.FilmPagingSource
import com.example.withfilms.data.remote.FilmService
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(
    private val filmService: FilmService
) {
    fun getTopFilms() = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = {
            FilmPagingSource(filmService)
        }
    ).flow.map {
        it.map { film ->
            film.toFilm()
        }
    }

    suspend fun getFilmDetailById(id: Int) =
        filmService.getFilmDetailById(id = id).toFilmDetail()

    suspend fun getFilmStaffByFilmId(filmId: Int) =
        filmService.getFilmStaffById(filmId = filmId).map {
            it.toStaff()
        }
}