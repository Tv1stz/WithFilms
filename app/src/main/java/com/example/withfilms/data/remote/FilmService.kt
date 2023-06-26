package com.example.withfilms.data.remote


import com.example.withfilms.data.remote.model.persondetail.PersonDetailDto
import com.example.withfilms.data.remote.model.filmdetail.FilmDetailDto
import com.example.withfilms.data.remote.model.films.GetFilmsResponse
import com.example.withfilms.data.remote.model.filmstaff.FilmStaffDto
import com.example.withfilms.data.remote.model.searchfilms.GetSearchFilmsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/"
private const val API_KEY = "cf90f99b-da85-43ad-8b29-35bf82b4f2d3"
private const val TOP_250 = "TOP_250_BEST_FILMS"

interface FilmService {

    @GET("v2.2/films/top/")
    suspend fun getTopFilms(
        @Header("X-API-KEY") key: String = API_KEY,
        @Query("type") type: String = TOP_250,
        @Query("page") page: Int,
    ): GetFilmsResponse

    @GET("v2.2/films/{id}")
    suspend fun getFilmDetailById(
        @Header("X-API-KEY") key: String = API_KEY,
        @Path("id") filmId: Int
    ): FilmDetailDto

    @GET("v1/staff/")
    suspend fun getFilmStaffById(
        @Header("X-API-KEY") key: String = API_KEY,
        @Query("filmId") filmId: Int
    ): List<FilmStaffDto>

    @GET("v2.1/films/search-by-keyword/")
    suspend fun getSearchFilmByKeyWord(
        @Header("X-API-KEY") key: String = API_KEY,
        @Query("keyword") keyWord: String,
        @Query("page") page: Int = 1,
    ): GetSearchFilmsResponse

    @GET("v1/staff/{id}")
    suspend fun getPersonDetailById(
        @Header("X-API-KEY") key: String = API_KEY,
        @Path("id") actorId: Int
    ): PersonDetailDto

    companion object {

        fun create(): FilmService {

            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()
                .create(FilmService::class.java)
        }
    }
}