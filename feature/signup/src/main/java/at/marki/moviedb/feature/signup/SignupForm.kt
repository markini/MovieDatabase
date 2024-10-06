package at.marki.moviedb.feature.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import at.marki.moviedb.core.designsystems.components.EmailTextField
import at.marki.moviedb.core.designsystems.components.PasswordTextField
import at.marki.moviedb.core.designsystems.components.StandardTextField
import at.marki.moviedb.core.designsystems.localCompositions.LocalHorizontalContentPadding
import at.marki.moviedb.feature.signup.ui.SubmitButton

private const val DEFAULT_FORM_SPACING = 10

@Composable
fun SignupForm(
    uiState: SignupViewModelUiState.Success,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordTwoChanged: (String) -> Unit,
    onSubmitClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = LocalHorizontalContentPadding.current,
                vertical = 30.dp,
            ),
    ) {
        StandardTextField(
            value = uiState.name,
            label = "Name",
            onTextChanged = onNameChanged,
        )
        Spacer(modifier = Modifier.height(DEFAULT_FORM_SPACING.dp))
        EmailTextField(
            value = uiState.mail,
            label = "Email",
            onTextChanged = onEmailChanged,
        )
        Spacer(modifier = Modifier.height(DEFAULT_FORM_SPACING.dp))
        PasswordTextField(
            value = uiState.password,
            label = "Password",
            onTextChanged = onPasswordChanged,
        )
        Spacer(modifier = Modifier.height(DEFAULT_FORM_SPACING.dp))
        PasswordTextField(
            value = uiState.passwordTwo,
            label = "Password",
            onTextChanged = onPasswordTwoChanged,
        )
        Spacer(modifier = Modifier.height((DEFAULT_FORM_SPACING * 2).dp))
        SubmitButton(
            isEnabled = uiState.isSubmitEnabled,
            onClick = onSubmitClicked,
        )
    }
}
