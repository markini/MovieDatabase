package com.bergfex.mobile.weather.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesMovieDbDatabase(
        @ApplicationContext context: Context,
    ): MovieDbDatabase = Room.databaseBuilder(
        context,
        MovieDbDatabase::class.java,
        MovieDbDatabase.DATABASE_NAME,
    ).build()
}
