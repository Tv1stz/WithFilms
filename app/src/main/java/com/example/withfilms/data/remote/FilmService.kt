package com.example.withfilms.data.remote


import com.example.withfilms.data.remote.model.FilmDto
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/"
private const val API_KEY = "c1193f3a-dba3-4cf4-bf0b-ba5d971f2773"
private const val TOP_250 = "TOP_250_BEST_FILMS"

interface FilmService {

    @GET("v2.2/films/top/")
    suspend fun getTopFilms(
        @Header("X-API-KEY") key: String = API_KEY,
        @Query("type") type: String = TOP_250,
        @Query("page") page: Int,
    ): FilmDto

    companion object {

        fun create(): FilmService {

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(FilmService::class.java)
        }
    }
}