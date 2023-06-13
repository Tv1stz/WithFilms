package com.example.withfilms.domain.model

data class ActorDetail(
    val age: Int,
    val birthday: String,
    val birthplace: String,
    val death: String,
    val deathplace: String,
    val facts: List<String>,
    val films: List<ActorFilms>,
    val growth: Int,
    val hasAwards: Int,
    val nameEn: String?,
    val nameRu: String?,
    val personId: Int,
    val posterUrl: String,
    val profession: String,
    val sex: String,
    val spouses: List<Spouse>,
    val webUrl: String
)
