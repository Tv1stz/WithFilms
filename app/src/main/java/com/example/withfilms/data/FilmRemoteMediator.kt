package com.example.withfilms.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.withfilms.data.db.FilmDatabase
import com.example.withfilms.data.db.FilmEntity
import com.example.withfilms.data.remote.FilmService
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class FilmRemoteMediator(
    private val filmDatabase: FilmDatabase,
    private val filmService: FilmService
) : RemoteMediator<Int, FilmEntity>() {

    var pageIndex = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, FilmEntity>
    ): MediatorResult {

        pageIndex = getPageIndex(loadType) ?: return MediatorResult.Success(
            endOfPaginationReached = true
        )

        return try {
            val films = filmService.getTopFilms(page = pageIndex).films

            filmDatabase.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    filmDatabase.dao.clearAllFilms()
                }

                val filmEntities = films.map { it.toLocalPagingFilm() }
                filmDatabase.dao.upsertAllFilms(filmEntities)
            }


            MediatorResult.Success(
                endOfPaginationReached = films.isEmpty()
            )

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }

        return pageIndex
    }
}