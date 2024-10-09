package at.marki.moviedb.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import at.marki.moviedb.core.data.model.SearchResult
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.components.LoadingState
import at.marki.moviedb.core.designsystems.theme.AppTheme

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        uiState = uiState.value,
        modifier = modifier,
    )
}

@Composable
fun SearchScreen(
    uiState: SearchViewModelUiState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (uiState) {
            SearchViewModelUiState.Loading -> LoadingState()
            is SearchViewModelUiState.Success -> SuccessState(
                uiState = uiState,
            )
        }
    }
}

@Composable
private fun SuccessState(
    uiState: SearchViewModelUiState.Success,
    modifier: Modifier = Modifier,
) {
}


@Composable
@ThemePreviews
fun SearchScreenPreview() {
    AppTheme {
        Surface {
            SearchScreen(
                uiState = SearchViewModelUiState.Success(
                    allMovies = emptyList(),
                    searchedMovies = SearchResult.InitialValue,
                ),
            )
        }
    }
}