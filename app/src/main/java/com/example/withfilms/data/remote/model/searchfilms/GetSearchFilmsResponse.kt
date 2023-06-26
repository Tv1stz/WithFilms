package com.example.withfilms.data.remote.model.searchfilms
data class GetSearchFilmsResponse(
    val films: List<SearchFilmDto>,
    val keyword: String,
    val pagesCount: Int,
    val searchFilmsCountResult: Int
)