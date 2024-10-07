package at.marki.moviedb.feature.overview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.marki.moviedb.core.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository,
) : ViewModel() {

    companion object {
        private const val TAG = "OverviewViewModel"
        private val DEFAULT_DEBOUNCE_TIME = 200.milliseconds
    }


    val uiState: StateFlow<OverviewViewModelUiState> =
        userRepository.isUserLoggedIn().map {
            OverviewViewModelUiState.Success(
                name = "marki"
            )
        }
            .debounce(
                DEFAULT_DEBOUNCE_TIME,
            ).stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = OverviewViewModelUiState.Loading,
            )
}

sealed interface OverviewViewModelUiState {
    data object Loading : OverviewViewModelUiState
    data class Success(
        val name: String,
    ) : OverviewViewModelUiState
}
