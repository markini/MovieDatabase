package at.marki.moviedb.feature.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.marki.moviedb.core.data.repository.MovieRepository
import at.marki.moviedb.core.data.repository.UserRepository
import at.marki.moviedb.core.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository,
) : ViewModel() {

    companion object {
        private const val TAG = "DetailsViewModel"
        private val DEFAULT_DEBOUNCE_TIME = 200.milliseconds
    }

    private val _movieId: MutableStateFlow<Long?> = MutableStateFlow(null)

    val uiState: StateFlow<DetailsViewModelUiState> = _movieId
        .flatMapLatest { id ->
            movieRepository.getMovie(id ?: 0)
        }.map { movie ->
            when (movie) {
                null -> DetailsViewModelUiState.Error
                else -> DetailsViewModelUiState.Success(
                    movie = movie,
                )
            }
        }.debounce(
            DEFAULT_DEBOUNCE_TIME,
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = DetailsViewModelUiState.Loading,
        )

    fun setMovieId(movieId: Long) {
        _movieId.value = movieId
    }
}

sealed interface DetailsViewModelUiState {
    data object Loading : DetailsViewModelUiState
    data class Success(
        val movie: Movie,
    ) : DetailsViewModelUiState

    data object Error : DetailsViewModelUiState
}
