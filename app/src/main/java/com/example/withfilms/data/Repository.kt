package com.example.withfilms.data

import androidx.paging.map
import com.example.withfilms.data.remote.NetworkDataSource
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(
    private val networkDataSource: NetworkDataSource
) {
    fun getTopFilms() =
        networkDataSource.getTopFilms().map {
            it.map { film ->
                film.toFilm()
            }
        }

    suspend fun getSearchFilmByKeyWord(searchQuery: String) =
        networkDataSource.getSearchFilmByKeyWord(searchQuery).films.map {
            it.toFilm()
        }


    suspend fun getFilmDetailById(filmId: Int) =
        networkDataSource.getFilmDetailById(filmId).toFilmDetail()


    suspend fun getFilmStaffByFilmId(filmId: Int) =
        networkDataSource.getFilmStaffByFilmId(filmId).map {
            it.toStaff()
        }

    suspend fun getActorDetailById(actorId: Int) =
        networkDataSource.getActorDetailById(actorId).toActorDetail()


}