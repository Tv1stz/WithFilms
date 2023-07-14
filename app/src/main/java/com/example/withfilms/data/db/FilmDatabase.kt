package com.example.withfilms.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [FilmEntity::class]
)
abstract class FilmDatabase : RoomDatabase() {

    abstract val dao: FilmDao
}