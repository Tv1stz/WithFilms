package com.example.withfilms.domain.model

data class PersonDetail(
    val personId: Int,
    val birthday: String,
    val death: String,
    val profession: String,
    val posterUrl: String,
    val nameRu: String,
    val nameEn: String,
    val films: List<PersonFilm>,
    val sex: String,
    val age: String,
)
