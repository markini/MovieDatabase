package at.marki.moviedb

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.marki.moviedb.core.data.domain.InitializeDatabaseUseCase
import at.marki.moviedb.core.data.domain.IsDatabaseInitializedUseCase
import at.marki.moviedb.core.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    //REVIEW: why no use case for the login state?
    userRepository: UserRepository,
    //REVIEW: not sure if the viewmodel should be aware of the initialization of the database
    // nor if it should be responsible for initializing it
    private val isDatabaseInitializedUseCase: IsDatabaseInitializedUseCase,
    private val initializeDatabaseUseCase: InitializeDatabaseUseCase,
) : ViewModel(), DefaultLifecycleObserver {

    companion object {
        private val DEFAULT_DEBOUNCE_TIME = 600.milliseconds
    }

    private val isDatabaseInitializedState = MutableStateFlow(false)

    val uiState: StateFlow<MainActivityUiState> =
        combine(
            isDatabaseInitializedState,
            userRepository.isUserLoggedIn()
        ) { isDatabaseInitialized, isUserLogged ->
            when {
                !isDatabaseInitialized -> MainActivityUiState.Loading
                else -> MainActivityUiState.Success(
                    isUserLoggedIn = isUserLogged,
                )
            }
        }.debounce(
            DEFAULT_DEBOUNCE_TIME,
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = MainActivityUiState.Loading,
        )

    init {
        initializeApp()
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
    }

    private fun initializeApp() {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isDatabaseInitializedUseCase()) {
                initializeDatabaseUseCase()
            }
            isDatabaseInitializedState.emit(true)
        }
    }

    sealed interface MainActivityUiState {
        data object Loading : MainActivityUiState
        data class Success(
            val isUserLoggedIn: Boolean,
        ) : MainActivityUiState
    }
}
