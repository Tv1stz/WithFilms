package com.example.withfilms.data.remote.model.films

data class FilmDto(
    val films: List<FilmNetwork>,
    val pagesCount: Int
)