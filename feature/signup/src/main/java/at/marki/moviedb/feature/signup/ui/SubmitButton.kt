package at.marki.moviedb.feature.signup.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.theme.AppTheme

private const val BUTTON_HEIGHT = 56

@Composable
internal fun SubmitButton(
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(BUTTON_HEIGHT.dp),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = MaterialTheme.colorScheme.onBackground,
            contentColor = MaterialTheme.colorScheme.background,
            disabledContainerColor = MaterialTheme.colorScheme.onBackground,
            disabledContentColor = MaterialTheme.colorScheme.background,
        ),
    ) {
        Text(
            text = "Sign up",
            fontSize = 20.sp,
            overflow = Ellipsis,
            maxLines = 1,
        )
    }
}

@ThemePreviews
@Composable
private fun SubmitButtonPreview() {
    AppTheme {
        Surface {
            SubmitButton(
                isEnabled = true,
                onClick = {},
            )
        }
    }
}
