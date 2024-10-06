package at.marki.moviedb.feature.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import at.marki.moviedb.core.designsystems.components.EmailTextField
import at.marki.moviedb.core.designsystems.components.PasswordTextField
import at.marki.moviedb.core.designsystems.components.StandardTextField
import at.marki.moviedb.core.designsystems.localCompositions.LocalHorizontalContentPadding

@Composable
fun SignupForm(
    uiState: SignupViewModelUiState.Success,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordTwoChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = LocalHorizontalContentPadding.current),
    ) {
        StandardTextField(
            value = uiState.name,
            label = "Name",
            onTextChanged = onNameChanged,
        )
        EmailTextField(
            value = uiState.mail,
            label = "Email",
            onTextChanged = onEmailChanged,
        )
        PasswordTextField(
            value = uiState.password,
            label = "Password",
            onTextChanged = onPasswordChanged,
        )
        PasswordTextField(
            value = uiState.passwordTwo,
            label = "Password",
            onTextChanged = onPasswordTwoChanged,
        )
    }
}
