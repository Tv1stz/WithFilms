package com.example.withfilms.domain.model

data class FilmDetail(
    val filmId: Long = 0,
    val filmName: String = "",
    val rating: String = "",
    val genres: List<Genre> = emptyList(),
    val description: String = "",
    val filmLength: Int = 0,
    val year: String = "",
    val posterPreview: String = "",
    val poster: String = ""
)
