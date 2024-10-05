package at.marki.moviedb.feature.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import at.marki.moviedb.feature.signup.SignupRoute

const val SIGNUP_ROUTE = "signup"


fun NavController.navigateToSignup(
    navOptions: NavOptions? = null,
) {
    this.navigate(SIGNUP_ROUTE, navOptions)
}

fun NavGraphBuilder.signupScreen(
    onNavigateToMovieDb: () -> Unit,
) {
    composable(route = SIGNUP_ROUTE) {
        SignupRoute()
    }
}
