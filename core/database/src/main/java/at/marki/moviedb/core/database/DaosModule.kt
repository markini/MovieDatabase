package com.bergfex.mobile.weather.core.database

import at.marki.moviedb.core.database.daos.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesMovieDao(
        database: MovieDbDatabase,
    ): MovieDao = database.movieDao()
}
