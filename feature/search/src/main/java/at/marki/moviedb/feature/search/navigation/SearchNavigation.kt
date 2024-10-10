package at.marki.moviedb.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import at.marki.moviedb.feature.search.SearchRoute

const val SEARCH_ROUTE = "search"


fun NavController.navigateToSearch(
    navOptions: NavOptions? = null,
) {
    this.navigate(SEARCH_ROUTE, navOptions)
}

fun NavGraphBuilder.searchScreen(
    onBackClick: () -> Unit,
) {
    composable(route = SEARCH_ROUTE) {
        SearchRoute(
            onBackClick = onBackClick,
        )
    }
}
