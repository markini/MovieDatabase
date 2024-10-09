package at.marki.moviedb.feature.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.marki.moviedb.core.data.repository.UserRepository
import at.marki.moviedb.core.datastore.User
import dagger.hilt.android.lifecycle.HiltViewModel
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
class SignupViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository,
) : ViewModel() {

    companion object {
        private const val TAG = "SignupViewModel"
        private val DEFAULT_DEBOUNCE_TIME = 200.milliseconds
    }

    private val hasSubmittedState = MutableStateFlow(false)
    private val nameState = MutableStateFlow("")
    private val mailState = MutableStateFlow("")
    private val passwordOneState = MutableStateFlow("")
    private val passwordTwoState = MutableStateFlow("")

    val uiState: StateFlow<SignupViewModelUiState> =
        combine(
            hasSubmittedState,
            nameState,
            mailState,
            passwordOneState,
            passwordTwoState,
        ) { hasSubmitted, name, mail, passwordOne, passwordTwo ->

            val isSubmitEnabled =
                mail.isNotEmpty() && passwordOne.isNotEmpty() && passwordTwo.isNotEmpty()

            SignupViewModelUiState.Success(
                name = name,
                mail = mail,
                password = passwordOne,
                passwordTwo = passwordTwo,
                isSubmitEnabled = isSubmitEnabled,
                isPasswordMatching = !hasSubmitted || (passwordOne == passwordTwo),
            )
        }.debounce(
            DEFAULT_DEBOUNCE_TIME,
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = SignupViewModelUiState.Loading,
        )

    fun onNameChanged(name: String) {
        nameState.value = name
    }

    fun onMailChanged(mail: String) {
        mailState.value = mail
    }

    fun onPasswordOneChanged(password: String) {
        passwordOneState.value = password
    }

    fun onPasswordTwoChanged(password: String) {
        passwordTwoState.value = password
    }

    fun onSubmitClicked() {
        if ((uiState.value as? SignupViewModelUiState.Success)?.isSubmitEnabled == false) return

        hasSubmittedState.value = true
        if (passwordOneState.value != passwordTwoState.value) return

        viewModelScope.launch {
            userRepository.setUser(User(nameState.value, mailState.value))
        }
    }
}

sealed interface SignupViewModelUiState {
    data object Loading : SignupViewModelUiState
    data class Success(
        val name: String,
        val mail: String,
        val password: String,
        val passwordTwo: String,
        val isSubmitEnabled: Boolean,
        val isPasswordMatching: Boolean = true,
    ) : SignupViewModelUiState
}
