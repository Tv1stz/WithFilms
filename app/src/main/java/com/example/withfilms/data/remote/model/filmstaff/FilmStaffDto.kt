package com.example.withfilms.data.remote.model.filmstaff

data class FilmStaffDto(
    val description: String?,
    val nameEn: String,
    val nameRu: String,
    val posterUrl: String,
    val professionKey: String,
    val professionText: String,
    val staffId: Int
)