package com.example.withfilms.data

import com.example.withfilms.data.remote.model.GenreNetwork
import com.example.withfilms.data.remote.model.filmdetail.FilmDetailDto
import com.example.withfilms.data.remote.model.filmstaff.FilmStaffDto
import com.example.withfilms.domain.model.FilmDetail
import com.example.withfilms.domain.model.FilmStaff
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

fun FilmStaffDto.toFilmStaff() = FilmStaff(
    description =description,
    nameEn = nameEn,
    nameRu = nameRu,
    posterUrl = posterUrl,
    professionKey = professionKey,
    professionText = professionText,
    staffId = staffId
)

fun GenreNetwork.toGenre() = Genre(genre = genre)

fun List<GenreNetwork>.toGenre() = map(GenreNetwork::toGenre)

