package com.example.withfilms.domain.repository

import androidx.paging.PagingData
import com.example.withfilms.domain.model.PersonDetail
import com.example.withfilms.domain.model.Film
import com.example.withfilms.domain.model.FilmDetail
import com.example.withfilms.domain.model.FilmStaff
import com.example.withfilms.util.Request
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getTopFilms(): Flow<PagingData<Film>>

    suspend fun getFilmDetailById(filmId: Int): Flow<Request<FilmDetail>>

    suspend fun getFilmStaffById(filmId: Int): Flow<Request<List<FilmStaff>>>

    suspend fun getPersonDetailById(personId: Int): Flow<Request<PersonDetail>>

    suspend fun getSearchFilmByKeyWord(searchQuery: String): Flow<Request<List<Film>>>
}