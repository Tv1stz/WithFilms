package com.example.withfilms.data.remote.model.persondetail

data class PersonDetailDto(
    val age: Int,
    val birthday: String?,
    val birthplace: String?,
    val death: String?,
    val deathplace: String?,
    val facts: List<String>,
    val films: List<PersonFilmDto>,
    val growth: Int,
    val hasAwards: Int,
    val nameEn: String?,
    val nameRu: String?,
    val personId: Int,
    val posterUrl: String,
    val profession: String,
    val sex: String,
    val spouses: List<SpouseDto>,
    val webUrl: String
)