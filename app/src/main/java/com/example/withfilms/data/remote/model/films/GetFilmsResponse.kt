package com.example.withfilms.data.remote.model.films

data class GetFilmsResponse(
    val films: List<FilmDto>,
    val pagesCount: Int
)