package at.marki.moviedb.feature.overview.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import at.marki.moviedb.feature.overview.OverviewRoute

const val OVERVIEW_ROUTE = "overview"


fun NavController.navigateToOverview(navOptions: NavOptions? = null) {
    this.navigate(OVERVIEW_ROUTE, navOptions)
}

fun NavGraphBuilder.overviewScreen(
    onNavigateToSearch: () -> Unit,
) {
    composable(route = OVERVIEW_ROUTE) {
        OverviewRoute(
            onNavigateToSearch = onNavigateToSearch,
        )
    }
}
