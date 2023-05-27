package com.example.withfilms.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.withfilms.data.remote.model.films.Film
import retrofit2.HttpException
import java.io.IOException

class FilmPagingSource(
    private val filmService: FilmService
): PagingSource<Int, Film>() {

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        return try {
            val pageIndex = params.key ?: 1
            val response = filmService.getTopFilms(page = pageIndex)

            LoadResult.Page(
                data = response.films,
                prevKey = null,
                nextKey = if (response.pagesCount == params.key) null else pageIndex + 1
            )
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: IOException) {
            return LoadResult.Error(e)
        }



    }

}