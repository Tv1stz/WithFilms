package com.example.withfilms.data.remote.model.actordetail

data class ActorDetailDto(
    val age: Int,
    val birthday: String?,
    val birthplace: String?,
    val death: String?,
    val deathplace: String?,
    val facts: List<String>,
    val films: List<ActorFilmsNetwork>,
    val growth: Int,
    val hasAwards: Int,
    val nameEn: String?,
    val nameRu: String?,
    val personId: Int,
    val posterUrl: String,
    val profession: String,
    val sex: String,
    val spouses: List<SpouseNetwork>,
    val webUrl: String
)