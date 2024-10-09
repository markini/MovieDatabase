package at.marki.moviedb.core.data.model

import at.marki.moviedb.core.model.Movie

sealed interface SearchResult {
    data object Loading : SearchResult
    data class Success(
        val searchedMovies: List<Movie>?,
    ) : SearchResult

    data object NotFound : SearchResult
    data object InitialValue : SearchResult
}