package at.marki.moviedb.feature.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.marki.moviedb.core.data.model.SearchResult
import at.marki.moviedb.core.data.repository.FavoritesRepository
import at.marki.moviedb.core.data.repository.MovieRepository
import at.marki.moviedb.core.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val favoritesRepository: FavoritesRepository,
    private val movieRepository: MovieRepository,
) : ViewModel() {

    companion object {
        private val DEFAULT_DEBOUNCE_TIME = 200.milliseconds
    }

    private val _query = MutableSharedFlow<String>()

    private val _searchResult = MutableStateFlow<SearchResult>(value = SearchResult.InitialValue)

    val uiState: StateFlow<SearchViewModelUiState> =
        combine(
            movieRepository.getAllMovies(),
            _searchResult,
            favoritesRepository.getFavoriteIds(),
        ) { allMovies, searchResult, favoriteIds ->
            SearchViewModelUiState.Success(
                favoriteIds = favoriteIds,
                allMovies = allMovies,
                searchedMovies = searchResult,
                query = (searchResult as? SearchResult.Success)?.query.orEmpty(),
            )
        }.debounce(
            DEFAULT_DEBOUNCE_TIME,
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = SearchViewModelUiState.Loading,
        )

    init {
        initMovieSearch()
    }

    private fun initMovieSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.searchMovies(_query)
                .collectLatest {
                    _searchResult.emit(it)
                }
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            //REVIEW: this causes a race condition when you quickly
            // delete a lot of characters in your search
            // as the search performed through _query is not done yet, i assume?
            // try to type "what" in the search and then delete all characters by longpressing
            // on backspace
            if (query.isBlank()) {
                _searchResult.emit(SearchResult.InitialValue)
                return@launch
            }

            _query.emit(query.trim())
        }
    }

    fun toggleFavorite(movieId: Long) {
        viewModelScope.launch {
            favoritesRepository.toggleFavoriteId(movieId)
        }
    }
}

sealed interface SearchViewModelUiState {
    data object Loading : SearchViewModelUiState
    data class Success(
        val favoriteIds: List<Long>,
        val allMovies: List<Movie>,
        val searchedMovies: SearchResult,
        val query: String,
    ) : SearchViewModelUiState
}
