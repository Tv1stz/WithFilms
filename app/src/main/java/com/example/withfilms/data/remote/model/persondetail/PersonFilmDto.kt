package com.example.withfilms.data.remote.model.persondetail

data class PersonFilmDto(
    val description: String,
    val filmId: Int,
    val general: Boolean,
    val nameEn: String?,
    val nameRu: String?,
    val professionKey: String,
    val rating: String?
)