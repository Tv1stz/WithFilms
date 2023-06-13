package com.example.withfilms.data.remote.model.searchfilms

import com.example.withfilms.data.remote.model.CountryNetwork
import com.example.withfilms.data.remote.model.GenreNetwork

data class SearchFilmNetwork(
    val countries: List<CountryNetwork>,
    val description: String,
    val filmId: Int,
    val filmLength: String,
    val genres: List<GenreNetwork>,
    val nameEn: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val rating: String?,
    val ratingVoteCount: Int,
    val type: String,
    val year: String
)