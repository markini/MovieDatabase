package at.marki.moviedb.feature.details

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
fun DetailsBottomSheet(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()


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

        }
    }
}