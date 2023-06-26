package com.example.withfilms.domain.model

data class FilmDetail(
    val filmId: Int,
    val posterUrl: String,
    val posterUrlPreview: String,
    val year: String,
    val name: String,
    val description: String,
    val genre: List<Genre>,
    val filmLength: String,
    val rating: String
)
