package com.example.withfilms.domain.model

data class ActorFilms(
    val description: String,
    val filmId: Int,
    val general: Boolean,
    val nameEn: String,
    val nameRu: String,
    val professionKey: String,
    val rating: String
)