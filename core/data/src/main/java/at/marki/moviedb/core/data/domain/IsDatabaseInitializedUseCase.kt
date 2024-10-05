package at.marki.moviedb.core.data.domain

import at.marki.moviedb.core.database.daos.MovieDao
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Checks if we have any movies in the database
 *
 * @return true if we have any movies in the database (database is initialized)
 */
class IsDatabaseInitializedUseCase @Inject constructor(
    private val movieDao: MovieDao,
) {
    suspend operator fun invoke(): Boolean {
        return movieDao.getAllMovies().first().isNotEmpty()
    }
}
