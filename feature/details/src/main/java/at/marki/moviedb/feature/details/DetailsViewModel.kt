package at.marki.moviedb.feature.details

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
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository,
) : ViewModel() {

    companion object {
        private const val TAG = "DetailsViewModel"
        private val DEFAULT_DEBOUNCE_TIME = 200.milliseconds
    }


    val uiState: StateFlow<DetailsViewModelUiState> =
        userRepository.isUserLoggedIn().map {
            DetailsViewModelUiState.Success(
                name = "marki"
            )
        }
            .debounce(
                DEFAULT_DEBOUNCE_TIME,
            ).stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = DetailsViewModelUiState.Loading,
            )
}

sealed interface DetailsViewModelUiState {
    data object Loading : DetailsViewModelUiState
    data class Success(
        val name: String,
    ) : DetailsViewModelUiState
}
