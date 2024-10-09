package at.marki.moviedb.feature.overview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.marki.moviedb.core.data.repository.FavoritesRepository
import at.marki.moviedb.core.data.repository.MovieRepository
import at.marki.moviedb.core.data.repository.UserRepository
import at.marki.moviedb.core.datastore.User
import at.marki.moviedb.core.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val favoritesRepository: FavoritesRepository,
    private val movieRepository: MovieRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    companion object {
        private const val TAG = "OverviewViewModel"
        private val DEFAULT_DEBOUNCE_TIME = 200.milliseconds

        private val STAFF_RECOMMENDATION_IDS = listOf(530915L, 186L, 77L, 246741L, 475557L)
    }


    val uiState: StateFlow<OverviewViewModelUiState> =
        combine(
            userRepository.getUser(),
            movieRepository.getFavoriteMovies(),
            movieRepository.getMovies(STAFF_RECOMMENDATION_IDS),
        ) { user, favorites, staffRecommendations ->
            when (user) {
                null -> OverviewViewModelUiState.Error
                else -> OverviewViewModelUiState.Success(
                    user = user,
                    favorites = favorites,
                    staffRecommendations = staffRecommendations,
                )
            }
        }.debounce(
            DEFAULT_DEBOUNCE_TIME,
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = OverviewViewModelUiState.Loading,
        )

    fun toggleFavorite(movieId: Long) {
        viewModelScope.launch {
            favoritesRepository.toggleFavoriteId(id = movieId)
        }
    }
}

sealed interface OverviewViewModelUiState {
    data object Loading : OverviewViewModelUiState
    data class Success(
        val user: User,
        val favorites: List<Movie>,
        val staffRecommendations: List<Movie>,
    ) : OverviewViewModelUiState

    data object Error : OverviewViewModelUiState
}
