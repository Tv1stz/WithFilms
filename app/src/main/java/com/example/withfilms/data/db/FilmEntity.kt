package com.example.withfilms.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "paging_films"
)
data class FilmEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "film_id")
    val filmId: Int,
    val name: String,
    @ColumnInfo(name = "poster_url")
    val posterUrl: String,
    val rating: String
)