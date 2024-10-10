package at.marki.moviedb.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import at.marki.moviedb.core.data.model.SearchResult
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.components.LoadingState
import at.marki.moviedb.core.designsystems.components.MovieListElement
import at.marki.moviedb.core.designsystems.localCompositions.LocalHorizontalContentPadding
import at.marki.moviedb.core.designsystems.theme.AppTheme
import at.marki.moviedb.core.model.Movie
import at.marki.moviedb.feature.search.ui.SearchAppbar
import at.marki.moviedb.feature.search.ui.SearchField

@Composable
fun SearchRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        uiState = uiState.value,
        onBackClick = onBackClick,
        onSearchMovies = { query -> viewModel.searchMovies(query) },
        modifier = modifier,
    )
}

@Composable
fun SearchScreen(
    uiState: SearchViewModelUiState,
    onBackClick: () -> Unit,
    onSearchMovies: (String) -> Unit,
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
                onBackClick = onBackClick,
                onOpenMovieDetails = { movieId -> },
                onSearchMovies = onSearchMovies,
            )
        }
    }
}

@Composable
private fun SuccessState(
    uiState: SearchViewModelUiState.Success,
    onBackClick: () -> Unit,
    onOpenMovieDetails: (Long) -> Unit,
    onSearchMovies: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        SearchAppbar(
            onBackClick = onBackClick,
            modifier = Modifier
                .fillMaxWidth(),
        )
        SearchField(
            initialQueryParameter = uiState.query,
            onSearchMovies = onSearchMovies,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalHorizontalContentPadding.current),
        )
        when (uiState.searchedMovies) {
            SearchResult.InitialValue -> MoviesList(
                movies = uiState.allMovies,
                onOpenMovieDetails = onOpenMovieDetails,
                modifier = Modifier.fillMaxSize(),
            )

            SearchResult.Loading -> LoadingState()
            SearchResult.NotFound -> MovieNotFound()
            is SearchResult.Success -> MoviesList(
                movies = uiState.searchedMovies.searchedMovies.orEmpty(),
                onOpenMovieDetails = onOpenMovieDetails,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Composable
private fun MoviesList(
    movies: List<Movie>,
    onOpenMovieDetails: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
    ) {
        items(movies) { movie ->
            MovieElement(
                movie = movie,
                onOpenMovieDetails = onOpenMovieDetails,
            )
        }
    }
}

@Composable
private fun MovieElement(
    movie: Movie,
    onOpenMovieDetails: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    MovieListElement(
        isFavorite = true,
        movie = movie,
        onToggleFavorite = {},
        modifier = modifier
            .fillMaxWidth()
            .clickable { onOpenMovieDetails(movie.id) }
            .padding(
                horizontal = LocalHorizontalContentPadding.current,
                vertical = 10.dp,
            ),
    )
}

@Composable
private fun MovieNotFound(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "No movies found",
            modifier = Modifier.align(Alignment.Center),
        )
    }
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
                    query = "",
                ),
                onBackClick = {},
                onSearchMovies = {},
            )
        }
    }
}