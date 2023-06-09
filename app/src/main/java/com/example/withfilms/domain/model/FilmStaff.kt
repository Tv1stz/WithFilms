package com.example.withfilms.domain.model

data class FilmStaff(
    val staffId: Int,
    val nameRu: String,
    val nameEn: String,
    val posterUrl: String,
    val professionText: String,
    val description: String,
)