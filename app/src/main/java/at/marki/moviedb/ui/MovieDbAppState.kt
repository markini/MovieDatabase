package at.marki.moviedb.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberWeatherAppState(
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
    coroutineScope: CoroutineScope,
    val navController: NavHostController,
) {
    private val currentRoute: String?
        get() = navController.currentDestination?.route
}
