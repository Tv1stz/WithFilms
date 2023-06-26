package com.example.withfilms.data

import com.example.withfilms.data.remote.model.GenreDto
import com.example.withfilms.data.remote.model.persondetail.PersonDetailDto
import com.example.withfilms.data.remote.model.persondetail.PersonFilmDto
import com.example.withfilms.data.remote.model.filmdetail.FilmDetailDto
import com.example.withfilms.data.remote.model.films.FilmDto
import com.example.withfilms.data.remote.model.filmstaff.FilmStaffDto
import com.example.withfilms.data.remote.model.searchfilms.SearchFilmDto
import com.example.withfilms.domain.model.PersonDetail
import com.example.withfilms.domain.model.PersonFilm
import com.example.withfilms.domain.model.Film
import com.example.withfilms.domain.model.FilmDetail
import com.example.withfilms.domain.model.FilmStaff
import com.example.withfilms.domain.model.Genre

private const val MALE = "Мужской"
private const val FEMALE = "Женский"
private const val NO_RATING = "0.0"
private const val NO_NAME = "нет имени"
private const val NO_DESCRIPTION = "нет описания"
private const val NOTHING = ""

fun FilmDto.toFilm() = Film(
    filmId = filmId,
    nameRu = nameRu,
    posterUrl = posterUrl,
    rating = (rating ?: 0.0).toString(),
)

fun SearchFilmDto.toFilm() = Film(
    filmId = filmId,
    nameRu = nameRu,
    posterUrl = posterUrl,
    rating = rating ?: NO_RATING
)

fun PersonDetailDto.toPersonDetail() = PersonDetail(
    personId = personId,
    birthday = birthday ?: NOTHING,
    death = death ?: NOTHING,
    profession = profession,
    posterUrl = posterUrl,
    nameEn = nameEn ?: NOTHING,
    nameRu = nameRu ?: NOTHING,
    films = films.toPersonFilm() ,
    sex = if (sex == MALE) MALE else FEMALE,
    age = age.toString()
)

fun PersonFilmDto.toPersonFilm() = PersonFilm(
    filmId = filmId,
    nameRu = nameRu ?: NOTHING,
    nameEn = nameEn ?: NOTHING,
    rating = (rating ?: 0.0).toString()
)

fun FilmDetailDto.toFilmDetail() = FilmDetail(
    filmId = kinopoiskId,
    posterUrl = posterUrl,
    posterUrlPreview = posterUrlPreview,
    year = year.toString(),
    name = nameRu ?: nameEn ?: NO_NAME,
    description = description ?: shortDescription ?: NO_DESCRIPTION,
    genre = genres.toGenre(),
    filmLength = filmLength.toString(),
    rating = (ratingKinopoisk ?: 0.0).toString()
)

fun FilmStaffDto.toFilmStaff() = FilmStaff(
    staffId = staffId,
    nameRu = nameRu,
    nameEn = nameEn,
    posterUrl = posterUrl,
    professionKey = professionKey,
    description = description ?: NOTHING
)

fun GenreDto.toGenre() = Genre(genre)

fun List<PersonFilmDto>.toPersonFilm() = map(PersonFilmDto::toPersonFilm)

fun List<GenreDto>.toGenre() = map(GenreDto::toGenre)


