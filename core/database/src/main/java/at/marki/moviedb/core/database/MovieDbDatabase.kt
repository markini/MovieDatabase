package com.bergfex.mobile.weather.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import at.marki.moviedb.core.database.daos.MovieDao

@Database(
    entities = [

    ],
    version = 1,
    autoMigrations = [
        // AutoMigration(from = 1, to = 2),
    ],
    exportSchema = true,
)
@TypeConverters()
abstract class MovieDbDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "moviedb-database"
    }

    abstract fun movieDao(): MovieDao
}
