package com.example.withfilms.data

import com.example.withfilms.data.remote.model.GenreNetwork
import com.example.withfilms.data.remote.model.CountryNetwork
import com.example.withfilms.data.remote.model.actordetail.ActorDetailDto
import com.example.withfilms.data.remote.model.actordetail.ActorFilmsNetwork
import com.example.withfilms.data.remote.model.actordetail.SpouseNetwork
import com.example.withfilms.data.remote.model.filmdetail.FilmDetailDto
import com.example.withfilms.data.remote.model.films.FilmNetwork
import com.example.withfilms.data.remote.model.filmstaff.FilmStaffDto
import com.example.withfilms.data.remote.model.searchfilms.SearchFilmNetwork
import com.example.withfilms.domain.model.ActorDetail
import com.example.withfilms.domain.model.ActorFilms
import com.example.withfilms.domain.model.Country
import com.example.withfilms.domain.model.Film
import com.example.withfilms.domain.model.FilmDetail
import com.example.withfilms.domain.model.Staff
import com.example.withfilms.domain.model.Genre
import com.example.withfilms.domain.model.Spouse

fun FilmDetailDto.toFilmDetail() = FilmDetail(
    filmId = kinopoiskId,
    filmName = nameRu ?: nameEn ?: nameOriginal ?: "",
    rating = (ratingKinopoisk ?: 0.0).toString(),
    genres = genres.toGenre(),
    description = description ?: "нет описания",
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

fun SpouseNetwork.toSpouse() = Spouse(
    children = children,
    divorced = divorced,
    divorcedReason = divorcedReason,
    name = name,
    personId = personId,
    relation = relation,
    sex = sex,
    webUrl = webUrl
)

fun ActorFilmsNetwork.toActorFilms() = ActorFilms(
    description = description,
    filmId = filmId,
    general = general,
    nameEn = nameEn ?: "",
    nameRu = nameRu ?: "",
    professionKey = professionKey,
    rating = rating ?: "0.0"
)

fun ActorDetailDto.toActorDetail() = ActorDetail(
    age = age,
    birthday = birthday ?: "",
    birthplace = birthplace ?: "",
    death = death ?: "",
    deathplace = deathplace ?: "",
    facts = facts,
    films = films.toActorFilms(),
    growth = growth,
    hasAwards = hasAwards,
    nameEn = nameEn ?: "",
    nameRu = nameRu ?: "",
    personId = personId,
    posterUrl = posterUrl,
    profession = profession,
    sex = sex,
    spouses = spouses.toSpouse(),
    webUrl = webUrl
)

fun GenreNetwork.toGenre() = Genre(genre = genre)

fun CountryNetwork.toCountry() = Country(country = country)

fun List<GenreNetwork>.toGenre() = map(GenreNetwork::toGenre)

fun List<CountryNetwork>.toCountry() = map(CountryNetwork::toCountry)

fun List<SpouseNetwork>.toSpouse() = map(SpouseNetwork::toSpouse)

fun List<ActorFilmsNetwork>.toActorFilms() = map(ActorFilmsNetwork::toActorFilms)