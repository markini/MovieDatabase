package at.marki.moviedb.feature.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.components.LoadingState
import at.marki.moviedb.core.designsystems.theme.AppTheme

@Composable
fun OverviewRoute(
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    OverviewScreen(
        uiState = uiState.value,
        modifier = modifier,
    )
}

@Composable
fun OverviewScreen(
    uiState: OverviewViewModelUiState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (uiState) {
            OverviewViewModelUiState.Loading -> LoadingState()
            is OverviewViewModelUiState.Success -> SuccessState(
                uiState = uiState,
            )
        }
    }
}

@Composable
private fun SuccessState(
    uiState: OverviewViewModelUiState.Success,
    modifier: Modifier = Modifier,
) {

}


@Composable
@ThemePreviews
fun OverviewScreenPreview() {
    AppTheme {
        Surface {
            OverviewScreen(
                uiState = OverviewViewModelUiState.Success(
                    name = "",
                ),
            )
        }
    }
}