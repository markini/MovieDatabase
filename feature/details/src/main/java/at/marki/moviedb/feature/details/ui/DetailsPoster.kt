package at.marki.moviedb.feature.details.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.theme.AppTheme
import at.marki.moviedb.core.model.Movie
import coil.compose.SubcomposeAsyncImage
import coil.imageLoader
import coil.request.ImageRequest

private const val POSTER_HEIGHT = 295
private const val POSTER_WIDTH = 203

@Composable
internal fun DetailsPoster(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(movie.posterUrl)
            .diskCacheKey(movie.posterUrl)
            .memoryCacheKey(movie.posterUrl)
            .build(),
        loading = { LoadingPlaceholder() },
        contentDescription = null,
        imageLoader = LocalContext.current.imageLoader,
        modifier = modifier
            .shadow(12.dp, Shapes().medium)
            .height(POSTER_HEIGHT.dp)
            .width(POSTER_WIDTH.dp)
            .clip(Shapes().medium),
        contentScale = ContentScale.FillBounds,
    )
}

@Composable
private fun LoadingPlaceholder(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(POSTER_HEIGHT.dp)
            .width(POSTER_WIDTH.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@ThemePreviews
@Composable
private fun MovieListElementPreview() {
    AppTheme {
        Surface {
            DetailsPoster(
                movie = Movie(
                    id = 1,
                    title = "Title",
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
            )
        }
    }
}
