package com.example.withfilms.data

import com.example.withfilms.data.remote.model.GenreNetwork
import com.example.withfilms.data.remote.model.CountryNetwork
import com.example.withfilms.data.remote.model.filmdetail.FilmDetailDto
import com.example.withfilms.data.remote.model.films.FilmNetwork
import com.example.withfilms.data.remote.model.filmstaff.FilmStaffDto
import com.example.withfilms.data.remote.model.searchfilms.SearchFilmNetwork
import com.example.withfilms.domain.model.Country
import com.example.withfilms.domain.model.Film
import com.example.withfilms.domain.model.FilmDetail
import com.example.withfilms.domain.model.Staff
import com.example.withfilms.domain.model.Genre

fun FilmDetailDto.toFilmDetail() = FilmDetail(
    filmId = kinopoiskId,
    filmName = nameRu ?: "no name",
    rating = (ratingKinopoisk ?: 0.0).toString(),
    genres = genres.toGenre(),
    description = description,
    filmLength = filmLength ?: 0,
    year = year.toString(),
    posterPreview = posterUrlPreview,
    poster = posterUrl
)

fun FilmStaffDto.toStaff() = Staff(
    description = description,
    nameEn = nameEn,
    nameRu = nameRu,
    posterUrl = posterUrl,
    professionKey = professionKey,
    professionText = professionText,
    staffId = staffId
)

fun FilmNetwork.toFilm() = Film(
    countries = countries.toCountry(),
    filmId = filmId,
    filmLength = filmLength,
    genres = genres.toGenre(),
    nameEn = nameEn,
    nameRu = nameRu,
    posterUrl = posterUrl,
    posterUrlPreview = posterUrlPreview,
    rating = (rating ?: 0.0).toString(),
    ratingChange = ratingChange,
    ratingVoteCount = ratingVoteCount,
    year = year,
    description = null,
    type = null
)

fun SearchFilmNetwork.toFilm() = Film(
    countries = countries.toCountry(),
    description = description,
    filmId = filmId,
    filmLength = filmLength,
    genres = genres.toGenre(),
    nameEn = nameEn,
    nameRu = nameRu,
    posterUrl = posterUrl,
    posterUrlPreview = posterUrlPreview,
    rating = (rating ?: 0.0).toString(),
    ratingVoteCount = ratingVoteCount,
    type = type,
    year = year,
    ratingChange = null
)

fun GenreNetwork.toGenre() = Genre(genre = genre)

fun CountryNetwork.toCountry() = Country(country = country)

fun List<GenreNetwork>.toGenre() = map(GenreNetwork::toGenre)

fun List<CountryNetwork>.toCountry() = map(CountryNetwork::toCountry)