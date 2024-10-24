package at.marki.moviedb.feature.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.marki.moviedb.core.data.repository.FavoritesRepository
import at.marki.moviedb.core.data.repository.MovieRepository
import at.marki.moviedb.core.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val favoritesRepository: FavoritesRepository,
    private val movieRepository: MovieRepository,
) : ViewModel() {

    companion object {
        private val DEFAULT_DEBOUNCE_TIME = 150.milliseconds
    }

    private val _movieId: MutableStateFlow<Long?> = MutableStateFlow(null)

    private val _uiState =
        MutableStateFlow<DetailsViewModelUiState>(DetailsViewModelUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val uiStateFlow = _movieId
        .flatMapLatest { id ->
            combine(
                movieRepository.getMovie(id ?: 0),
                favoritesRepository.isFavoriteId(id ?: 0),
            ) { movie, isFavorite ->
                when (movie) {
                    null -> DetailsViewModelUiState.Error
                    else -> DetailsViewModelUiState.Success(
                        isFavorite = isFavorite,
                        movie = movie,
                    )
                }
            }
        }

    init {
        initializeUiState()
    }

    private fun initializeUiState() {
        viewModelScope.launch {
            uiStateFlow
                .debounce(DEFAULT_DEBOUNCE_TIME)
                .collectLatest {
                    _uiState.value = it
                }
        }
    }

    fun resetUiState() {
        _uiState.value = DetailsViewModelUiState.Loading
    }

    fun setMovieId(movieId: Long) {
        _movieId.value = movieId
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            val movie = (uiState.value as? DetailsViewModelUiState.Success)?.movie ?: return@launch
            favoritesRepository.toggleFavoriteId(movie.id)
        }
    }
}

sealed interface DetailsViewModelUiState {
    data object Loading : DetailsViewModelUiState
    data class Success(
        val isFavorite: Boolean,
        val movie: Movie,
    ) : DetailsViewModelUiState

    data object Error : DetailsViewModelUiState
}
