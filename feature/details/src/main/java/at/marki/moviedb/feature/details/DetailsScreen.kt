package at.marki.moviedb.feature.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.components.ErrorState
import at.marki.moviedb.core.designsystems.components.LoadingState
import at.marki.moviedb.core.designsystems.components.RatingStarsRow
import at.marki.moviedb.core.designsystems.localCompositions.LocalHorizontalContentPadding
import at.marki.moviedb.core.designsystems.theme.AppTheme
import at.marki.moviedb.core.model.Movie
import at.marki.moviedb.feature.details.ui.DetailAppBar
import at.marki.moviedb.feature.details.ui.DetailsPoster
import at.marki.moviedb.feature.details.ui.DetailsText
import at.marki.moviedb.feature.details.ui.KeyFacts
import at.marki.moviedb.feature.details.ui.MovieHeadlineInformation
import kotlinx.coroutines.launch

/**
 * Details Bottom Sheet. Shows details for a movie.
 * Doesn't have a navigation. Should be shown in other screens.
 *
 * @param movieId The ID of the movie to show details for.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsBottomSheet(
    movieId: Long,
    onDismissBottomSheet: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    viewModel.setMovieId(movieId)

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val uiStateValue = viewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        sheetState = sheetState,
        modifier = modifier.fillMaxSize(),
        onDismissRequest = onDismissBottomSheet,
        scrimColor = Color.Transparent,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        tonalElevation = 0.dp,
    ) {
        BottomSheetContent(
            uiState = uiStateValue.value,
            modifier = modifier,
            onToggleFavorite = { viewModel.toggleFavorite() },
            onDismiss = {
                scope.launch {
                    sheetState.hide()
                    onDismissBottomSheet()
                }
            },
        )
    }
}

@Composable
private fun BottomSheetContent(
    uiState: DetailsViewModelUiState,
    onToggleFavorite: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = LocalHorizontalContentPadding.current),
    ) {
        when (uiState) {
            is DetailsViewModelUiState.Loading -> LoadingState()

            is DetailsViewModelUiState.Success -> SuccessState(
                uiState = uiState,
                onToggleFavorite = onToggleFavorite,
                onDismiss = onDismiss,
            )

            is DetailsViewModelUiState.Error -> ErrorState()
        }
    }
}

@Composable
private fun SuccessState(
    uiState: DetailsViewModelUiState.Success,
    onToggleFavorite: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            DetailAppBar(
                isFavorite = uiState.isFavorite,
                onToggleFavorite = onToggleFavorite,
                onDismiss = onDismiss,
                modifier = Modifier.padding(bottom = 20.dp),
            )
        }
        item {
            DetailsPoster(
                movie = uiState.movie,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }
        item {
            RatingStarsRow(
                rating = uiState.movie.rating?.toInt() ?: 0,
                modifier = Modifier.padding(bottom = 6.dp),
                starSize = 30,
            )
        }
        item {
            MovieHeadlineInformation(
                movie = uiState.movie,
                modifier = Modifier.padding(bottom = 40.dp),
            )
        }
        item {
            DetailsText(
                movie = uiState.movie,
                modifier = Modifier.padding(bottom = 40.dp),
            )
        }
        item {
            KeyFacts(
                movie = uiState.movie,
                modifier = Modifier.padding(bottom = 40.dp),
            )
        }
    }
}


@Composable
@ThemePreviews
fun DetailsScreenPreview() {
    AppTheme {
        Surface {
            BottomSheetContent(
                uiState = DetailsViewModelUiState.Success(
                    isFavorite = true,
                    movie = Movie(
                        id = 1,
                        title = "Movie Title",
                        rating = 4.0,
                        revenue = 1000000,
                        releaseDate = null,
                        posterUrl = "",
                        runtime = 120,
                        overview = "Overview",
                        reviews = 100,
                        budget = 1000000,
                        language = "en",
                        genres = listOf("Action", "Adventure"),
                        director = null,
                        cast = emptyList(),
                    ),
                ),
                onToggleFavorite = {},
                onDismiss = {},
            )
        }
    }
}
