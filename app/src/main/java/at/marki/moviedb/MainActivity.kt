package at.marki.moviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import at.marki.moviedb.MainActivityViewModel.MainActivityUiState
import at.marki.moviedb.core.designsystems.components.LoadingState
import at.marki.moviedb.core.designsystems.theme2.AppTheme
import at.marki.moviedb.ui.MovieDbApp
import at.marki.moviedb.ui.rememberMovieDbAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Observe the lifecycle of the ViewModel
        lifecycle.addObserver(viewModel)

        setContent {
            AppTheme {

                val movieDbAppState = rememberMovieDbAppState()

                when (val uiState = viewModel.uiState.collectAsStateWithLifecycle().value) {
                    is MainActivityUiState.Loading -> LoadingState()
                    is MainActivityUiState.Success -> MovieDbApp(
                        isUserLoggedIn = uiState.isUserLoggedIn,
                        appState = movieDbAppState,
                    )
                }
            }
        }
    }
}
