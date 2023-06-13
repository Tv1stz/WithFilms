package com.example.withfilms.data.remote.model.actordetail

data class ActorFilmsNetwork(
    val description: String,
    val filmId: Int,
    val general: Boolean,
    val nameEn: String?,
    val nameRu: String?,
    val professionKey: String,
    val rating: String?
)