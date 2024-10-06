package at.marki.moviedb.feature.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.theme.MovieDatabaseTheme
import at.marki.moviedb.core.designsystems.theme2.AppTheme

@Composable
fun SignupRoute(
    modifier: Modifier = Modifier,
    viewModel: SignupViewModel = hiltViewModel(),
) {
    SignupScreen()
}

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Signup",
        )
    }
}

@Composable
@ThemePreviews
fun SignupScreenPreview() {
    AppTheme {
        Surface {
            SignupScreen()
        }
    }
}