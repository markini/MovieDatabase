package at.marki.moviedb.core.data.repository

import at.marki.moviedb.core.database.daos.MovieDao
import at.marki.moviedb.core.database.relations.MovieWithCast
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
) {
    fun getAllMovies(): Flow<List<MovieWithCast>> {
        return movieDao.getAllMovies()
    }
}
