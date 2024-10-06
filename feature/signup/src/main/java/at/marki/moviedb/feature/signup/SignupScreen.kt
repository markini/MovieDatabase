package at.marki.moviedb.feature.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.components.LoadingState
import at.marki.moviedb.core.designsystems.theme.AppTheme

@Composable
fun SignupRoute(
    modifier: Modifier = Modifier,
    viewModel: SignupViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    SignupScreen(
        uiState = uiState.value,
        onNameChanged = viewModel::onNameChanged,
        onEmailChanged = viewModel::onMailChanged,
        onPasswordChanged = viewModel::onPasswordOneChanged,
        onPasswordTwoChanged = viewModel::onPasswordTwoChanged,
        onSubmitClicked = viewModel::onSubmitClicked,
        modifier = modifier,
    )
}

@Composable
fun SignupScreen(
    uiState: SignupViewModelUiState,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordTwoChanged: (String) -> Unit,
    onSubmitClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (uiState) {
            SignupViewModelUiState.Loading -> LoadingState()
            is SignupViewModelUiState.Success -> SuccessState(
                uiState = uiState,
                onNameChanged = onNameChanged,
                onEmailChanged = onEmailChanged,
                onPasswordChanged = onPasswordChanged,
                onPasswordTwoChanged = onPasswordTwoChanged,
                onSubmitClicked = onSubmitClicked,
            )
        }
    }
}

@Composable
private fun SuccessState(
    uiState: SignupViewModelUiState.Success,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordTwoChanged: (String) -> Unit,
    onSubmitClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            SignupHeader()
        }
        item {
            SignupForm(
                uiState = uiState,
                onNameChanged = onNameChanged,
                onEmailChanged = onEmailChanged,
                onPasswordChanged = onPasswordChanged,
                onPasswordTwoChanged = onPasswordTwoChanged,
                onSubmitClicked = onSubmitClicked,
            )
        }
    }
}


@Composable
@ThemePreviews
fun SignupScreenPreview() {
    AppTheme {
        Surface {
            SignupScreen(
                uiState = SignupViewModelUiState.Success(
                    name = "",
                    mail = "",
                    password = "",
                    passwordTwo = "",
                    isSubmitEnabled = true,
                ),
                onNameChanged = {},
                onEmailChanged = {},
                onPasswordChanged = {},
                onPasswordTwoChanged = {},
                onSubmitClicked = {},
            )
        }
    }
}