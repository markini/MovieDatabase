package at.marki.moviedb.core.database

import at.marki.moviedb.core.database.daos.CastDao
import at.marki.moviedb.core.database.daos.DirectorDao
import at.marki.moviedb.core.database.daos.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesCastDao(
        database: MovieDbDatabase,
    ): CastDao = database.castDao()

    @Provides
    fun providesDirectorDao(
        database: MovieDbDatabase,
    ): DirectorDao = database.directorDao()

    @Provides
    fun providesMovieDao(
        database: MovieDbDatabase,
    ): MovieDao = database.movieDao()
}
