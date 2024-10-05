package at.marki.moviedb.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import at.marki.moviedb.feature.signup.navigation.SIGNUP_ROUTE
import at.marki.moviedb.feature.signup.navigation.navigateToSignup
import at.marki.moviedb.feature.signup.navigation.signupScreen
import at.marki.moviedb.ui.MovieDbAppState

@Composable
fun MovieDbNavHost(
    appState: MovieDbAppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = SIGNUP_ROUTE,
        modifier = modifier,
    ) {
        signupScreen(
            onNavigateToMovieDb = navController::navigateToSignup,
        )
    }
}
