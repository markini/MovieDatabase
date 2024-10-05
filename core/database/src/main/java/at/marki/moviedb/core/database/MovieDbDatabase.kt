package at.marki.moviedb.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import at.marki.moviedb.core.database.daos.CastDao
import at.marki.moviedb.core.database.daos.DirectorDao
import at.marki.moviedb.core.database.daos.MovieDao
import at.marki.moviedb.core.database.model.CastEntity
import at.marki.moviedb.core.database.model.DirectorEntity
import at.marki.moviedb.core.database.model.MovieEntity
import at.marki.moviedb.core.database.typeConverter.LocalDateConverter

@Database(
    entities = [
        CastEntity::class,
        DirectorEntity::class,
        MovieEntity::class,
    ],
    version = 1,
    autoMigrations = [
        // AutoMigration(from = 1, to = 2),
    ],
    exportSchema = true,
)
@TypeConverters(
    LocalDateConverter::class,
)
abstract class MovieDbDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "moviedb-database"
    }

    abstract fun castDao(): CastDao
    abstract fun directorDao(): DirectorDao
    abstract fun movieDao(): MovieDao
}
