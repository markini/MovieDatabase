package at.marki.moviedb.feature.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

}

sealed interface SignupViewModelUiState {

}
