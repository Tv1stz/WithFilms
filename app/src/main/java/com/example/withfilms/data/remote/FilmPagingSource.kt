package com.example.withfilms.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.withfilms.data.remote.model.films.FilmNetwork
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FilmPagingSource @Inject constructor(
    private val filmService: FilmService
): PagingSource<Int, FilmNetwork>() {
    override fun getRefreshKey(state: PagingState<Int, FilmNetwork>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmNetwork> {

        return try {
            val pageNumber = params.key ?: 1
            val response = filmService.getTopFilms(page = pageNumber)

            val prevKey = if(pageNumber > 1) pageNumber - 1 else null
            val nextKey = if(response.films.isNotEmpty()) pageNumber + 1 else null

            LoadResult.Page(
                data = response.films,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}

