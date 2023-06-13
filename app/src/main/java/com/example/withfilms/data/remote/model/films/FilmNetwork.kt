package com.example.withfilms.data.remote.model.films

import com.example.withfilms.data.remote.model.CountryNetwork
import com.example.withfilms.data.remote.model.GenreNetwork

data class FilmNetwork(
    val countries: List<CountryNetwork>,
    val filmId: Int,
    val filmLength: String,
    val genres: List<GenreNetwork>,
    val nameEn: String?,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val rating: String?,
    val ratingChange: String?,
    val ratingVoteCount: Int,
    val year: String
)