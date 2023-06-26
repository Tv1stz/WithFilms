package com.example.withfilms.data.remote.model.searchfilms

import com.example.withfilms.data.remote.model.CountryDto
import com.example.withfilms.data.remote.model.GenreDto

data class SearchFilmDto(
    val countries: List<CountryDto>,
    val description: String,
    val filmId: Int,
    val filmLength: String,
    val genres: List<GenreDto>,
    val nameEn: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val rating: String?,
    val ratingVoteCount: Int,
    val type: String,
    val year: String
)