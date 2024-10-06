package at.marki.moviedb.core.data.repository

import at.marki.moviedb.core.database.daos.MovieDao
import at.marki.moviedb.core.database.relations.toModel
import at.marki.moviedb.core.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val favoritesRepository: FavoritesRepository,
) {
    fun getAllMovies(): Flow<List<Movie>> {
        return movieDao
            .getAllMovies()
            .map { movies ->
                movies.map { movieWithCast ->
                    movieWithCast.toModel()
                }
            }
    }

    fun getFavoriteMovies(): Flow<List<Movie>> {
        return favoritesRepository
            .getFavoriteIds()
            .flatMapLatest { favoriteIds ->
                movieDao
                    .getMoviesByIds(favoriteIds)
                    .map { movies ->
                        movies.map { movieWithCast ->
                            movieWithCast.toModel()
                        }
                    }
            }
    }

    fun getMovies(ids: List<Long>): Flow<List<Movie>> {
        return movieDao
            .getMoviesByIds(ids)
            .map { movies ->
                movies.map { movieWithCast ->
                    movieWithCast.toModel()
                }
            }
    }
}
