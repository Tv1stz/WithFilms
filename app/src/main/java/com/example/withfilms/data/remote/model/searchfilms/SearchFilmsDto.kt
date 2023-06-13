package com.example.withfilms.data.remote.model.searchfilms

data class SearchFilmsDto(
    val films: List<SearchFilmNetwork>,
    val keyword: String,
    val pagesCount: Int,
    val searchFilmsCountResult: Int
)