package at.marki.moviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import at.marki.moviedb.core.designsystems.theme.MovieDatabaseTheme
import at.marki.moviedb.ui.MovieDbApp
import at.marki.moviedb.ui.rememberMovieDbAppState

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Observe the lifecycle of the ViewModel
        lifecycle.addObserver(viewModel)

        setContent {
            MovieDatabaseTheme {
                val movieDbAppState = rememberMovieDbAppState()

                MovieDbApp(
                    appState = movieDbAppState,
                )
            }
        }
    }
}
