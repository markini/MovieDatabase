package at.marki.moviedb.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

/**
 * In this class we store the current state of the app.
 *
 * Currently we only store the current route. In the future we could provide information about
 * whether to show a bottom bar or the orientation of the current route.
 */
@Composable
fun rememberMovieDbAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): MovieDbAppState {
    return remember(
        coroutineScope,
        navController,
    ) {
        MovieDbAppState(
            coroutineScope = coroutineScope,
            navController = navController,
        )
    }
}

@Stable
class MovieDbAppState(
    val coroutineScope: CoroutineScope,
    val navController: NavHostController,
) {
    /**
     * Attention, on orientation change on some devices the current route can be briefly null.
     */
    private val currentRoute: String?
        get() = navController.currentDestination?.route
}
