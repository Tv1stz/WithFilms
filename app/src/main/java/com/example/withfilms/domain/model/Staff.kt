package com.example.withfilms.domain.model

data class Staff(
    val description: String?,
    val nameEn: String,
    val nameRu: String,
    val posterUrl: String,
    val professionKey: String,
    val professionText: String,
    val staffId: Int
)
