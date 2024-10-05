package at.marki.moviedb.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import at.marki.moviedb.core.designsystems.localCompositions.LocalThemeIsDark
import at.marki.moviedb.navigation.MovieDbNavHost

@Composable
fun MovieDbApp(
    isUserLoggedIn: Boolean,
    appState: MovieDbAppState,
) {
    CompositionLocalProvider(
        LocalThemeIsDark provides isSystemInDarkTheme(),
    ) {
        Surface {
            Scaffold(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    MovieDbNavHost(
                        isUserLoggedIn = isUserLoggedIn,
                        appState = appState,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}
