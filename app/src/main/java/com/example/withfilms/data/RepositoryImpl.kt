package com.example.withfilms.data

import androidx.paging.map
import com.example.withfilms.data.remote.NetworkDataSource
import com.example.withfilms.domain.repository.Repository
import com.example.withfilms.util.RequestUtils.requestFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
): Repository {

    override fun getTopFilms() =
        networkDataSource.getTopFilms().map {
            it.map { film ->
                film.toFilm()
            }
        }

    override suspend fun getSearchFilmByKeyWord(searchQuery: String) =
        requestFlow {
            val response = networkDataSource.getSearchFilmByKeyWord(searchQuery)
            response.films.map {
                it.toFilm()
            }
        }

    override suspend fun getFilmDetailById(filmId: Int) =
        requestFlow {
            val response = networkDataSource.getFilmDetailById(filmId)
            response.toFilmDetail()
        }

    override suspend fun getFilmStaffById(filmId: Int) =
        requestFlow {
            val response = networkDataSource.getFilmStaffById(filmId)
            response.map {
                it.toFilmStaff()
            }
        }

    override suspend fun getPersonDetailById(personId: Int) =
        requestFlow {
            val response = networkDataSource.getPersonDetailById(personId)
            response.toPersonDetail()
        }
}