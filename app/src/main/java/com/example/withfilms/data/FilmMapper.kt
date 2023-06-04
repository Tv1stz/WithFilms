package com.example.withfilms.data

import com.example.withfilms.data.remote.model.GenreNetwork
import com.example.withfilms.data.remote.model.NetworkCountry
import com.example.withfilms.data.remote.model.filmdetail.FilmDetailDto
import com.example.withfilms.data.remote.model.films.NetworkFilm
import com.example.withfilms.data.remote.model.filmstaff.FilmStaffDto
import com.example.withfilms.domain.model.Country
import com.example.withfilms.domain.model.Film
import com.example.withfilms.domain.model.FilmDetail
import com.example.withfilms.domain.model.Staff
import com.example.withfilms.domain.model.Genre

fun FilmDetailDto.toFilmDetail() = FilmDetail(
    filmId = kinopoiskId.toLong(),
    filmName = nameRu,
    rating = ratingKinopoisk.toString(),
    genres = genres.toGenre(),
    description = description,
    filmLength = filmLength,
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

fun NetworkFilm.toFilm() = Film(
    countries = countries.toCountry(),
    filmId = filmId,
    filmLength = filmLength,
    genres = genres.toGenre(),
    nameEn = nameEn,
    nameRu = nameRu,
    posterUrl = posterUrl,
    posterUrlPreview = posterUrlPreview,
    rating = rating,
    ratingChange = ratingChange,
    ratingVoteCount = ratingVoteCount,
    year = year
)

fun GenreNetwork.toGenre() = Genre(genre = genre)

fun NetworkCountry.toCountry() = Country(country = country)

fun List<GenreNetwork>.toGenre() = map(GenreNetwork::toGenre)

fun List<NetworkCountry>.toCountry() = map(NetworkCountry::toCountry)