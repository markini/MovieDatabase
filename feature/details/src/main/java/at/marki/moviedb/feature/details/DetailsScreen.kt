package at.marki.moviedb.feature.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.components.ErrorState
import at.marki.moviedb.core.designsystems.components.LoadingState
import at.marki.moviedb.core.designsystems.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsBottomSheet(
    movieId: Long,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    viewModel.setMovieId(movieId)
    val uiStateValue = viewModel.uiState.collectAsStateWithLifecycle()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
    ) {
        BottomSheetContent(
            uiState = uiStateValue.value,
            modifier = modifier,
        )
    }
}

@Composable
private fun BottomSheetContent(
    uiState: DetailsViewModelUiState,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (uiState) {
            is DetailsViewModelUiState.Loading -> LoadingState()

            is DetailsViewModelUiState.Success -> SuccessState(
                uiState = uiState,
            )

            is DetailsViewModelUiState.Error -> ErrorState()
        }
    }
}

@Composable
private fun SuccessState(
    uiState: DetailsViewModelUiState.Success,
    modifier: Modifier = Modifier,
) {

}


@Composable
@ThemePreviews
fun DetailsScreenPreview() {
    AppTheme {
        Surface {
            BottomSheetContent(
                uiState = DetailsViewModelUiState.Error,
            )
        }
    }
}
