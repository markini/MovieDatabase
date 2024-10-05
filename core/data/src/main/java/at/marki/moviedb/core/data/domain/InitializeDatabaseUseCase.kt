package at.marki.moviedb.core.data.domain

import android.content.Context
import at.marki.moviedb.core.data.R
import at.marki.moviedb.core.data.model.toEntities
import at.marki.moviedb.core.data.model.toEntity
import at.marki.moviedb.core.database.daos.CastDao
import at.marki.moviedb.core.database.daos.DirectorDao
import at.marki.moviedb.core.database.daos.MovieDao
import at.marki.moviedb.core.model.dtos.MovieDto
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import java.io.InputStream
import javax.inject.Inject

/**
 * Initialize the database with the movies from the movies.json in the raw folder
 */
class InitializeDatabaseUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val movieDao: MovieDao,
    private val castDao: CastDao,
    private val directorDao: DirectorDao,
) {
    suspend operator fun invoke() {
        val movieDto: List<MovieDto> = Json.decodeFromString(getMoviesJson())
        movieDto.forEach { saveMovie(it) }
    }

    private suspend fun saveMovie(movieDto: MovieDto) {
        movieDao.insertMovie(movieDto.toEntity())
        castDao.insertCasts(movieDto.cast.toEntities(movieDto.id))
        movieDto.director?.let { directorDao.insertDirector(it.toEntity(movieDto.id)) }
    }

    private fun getMoviesJson(): String {
        val inputStream: InputStream = context.resources.openRawResource(R.raw.movies)
        return inputStream
            .bufferedReader()
            .use { it.readText() }
    }
}
