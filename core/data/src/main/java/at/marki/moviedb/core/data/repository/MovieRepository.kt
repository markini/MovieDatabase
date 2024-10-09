package at.marki.moviedb.core.data.repository

import at.marki.moviedb.core.data.model.SearchResult
import at.marki.moviedb.core.database.daos.MovieDao
import at.marki.moviedb.core.database.relations.toModel
import at.marki.moviedb.core.database.relations.toModels
import at.marki.moviedb.core.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

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

    fun getMovie(id: Long): Flow<Movie?> {
        return movieDao
            .getMovieById(id)
            .map {
                it?.toModel()
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

    fun searchMovies(query: SharedFlow<String>) = query
        .debounce(100.milliseconds)
        .flatMapLatest { searchQuery ->
            if (searchQuery.isBlank()) {
                flow {
                    SearchResult.InitialValue
                }
            } else {
                movieDao.searchMovies(searchQuery)
                    .map { it.toModels() }
                    .map { movies ->
                        when {
                            movies.isNotEmpty() -> SearchResult.Success(movies)
                            else -> SearchResult.NotFound
                        }
                    }
            }
        }
}
