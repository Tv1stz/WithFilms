package com.example.withfilms.data.remote.model.films

data class FilmDto(
    val films: List<NetworkFilm>,
    val pagesCount: Int
)