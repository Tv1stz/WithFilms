package com.example.withfilms.data.remote.model.films

import com.example.withfilms.data.remote.model.CountryDto
import com.example.withfilms.data.remote.model.GenreDto

data class FilmDto(
    val countries: List<CountryDto>,
    val filmId: Int,
    val filmLength: String,
    val genres: List<GenreDto>,
    val nameEn: String?,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val rating: String?,
    val ratingChange: String?,
    val ratingVoteCount: Int,
    val year: String
)