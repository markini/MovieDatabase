package at.marki.moviedb.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import at.marki.moviedb.feature.overview.navigation.OVERVIEW_ROUTE
import at.marki.moviedb.feature.overview.navigation.navigateToOverview
import at.marki.moviedb.feature.overview.navigation.overviewScreen
import at.marki.moviedb.feature.search.navigation.navigateToSearch
import at.marki.moviedb.feature.search.navigation.searchScreen
import at.marki.moviedb.feature.signup.navigation.SIGNUP_ROUTE
import at.marki.moviedb.feature.signup.navigation.navigateToSignup
import at.marki.moviedb.feature.signup.navigation.signupScreen
import at.marki.moviedb.ui.MovieDbAppState

@Composable
fun MovieDbNavHost(
    isUserLoggedIn: Boolean,
    appState: MovieDbAppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController

    NavigateToOverviewIfUserIsLoggedIn(
        isUserLoggedIn = isUserLoggedIn,
        navController = navController,
    )

    NavigateToSignUpIfUserIsLoggedOut(
        isUserLoggedIn = isUserLoggedIn,
        navController = navController,
    )

    NavHost(
        navController = navController,
        startDestination = if (!isUserLoggedIn) SIGNUP_ROUTE else OVERVIEW_ROUTE,
        modifier = modifier,
    ) {
        signupScreen()
        overviewScreen(
            onNavigateToSearch = navController::navigateToSearch,
        )
        searchScreen(
            onBackClick = navController::popBackStack,
        )
    }
}

@Composable
private fun NavigateToOverviewIfUserIsLoggedIn(
    isUserLoggedIn: Boolean,
    navController: NavController,
) {
    // If he was initially logged in, then the start route is overview --> nothing to do
    val isUserLoggedInInitially = remember { isUserLoggedIn }

    LaunchedEffect(isUserLoggedIn) {
        if (!isUserLoggedInInitially && isUserLoggedIn) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(OVERVIEW_ROUTE, true)
                .build()

            navController.navigateToOverview(navOptions)
        }
    }
}

@Composable
private fun NavigateToSignUpIfUserIsLoggedOut(
    isUserLoggedIn: Boolean,
    navController: NavController,
) {
    LaunchedEffect(isUserLoggedIn) {
        if (!isUserLoggedIn) {
            if (navController.currentDestination?.route != SIGNUP_ROUTE) {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(SIGNUP_ROUTE, true)
                    .build()

                navController.navigateToSignup(navOptions)
            }
        }
    }
}
