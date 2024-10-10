package at.marki.moviedb.core.designsystems.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.theme.AppTheme
import at.marki.moviedb.core.model.Movie
import coil.compose.SubcomposeAsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

private const val POSTER_HEIGHT = 90
private const val POSTER_WIDTH = 60

@Composable
fun MovieListElement(
    isFavorite: Boolean,
    movie: Movie,
    onToggleFavorite: (movieId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
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
            modifier = Modifier
                .clip(Shapes().medium)
                .height(POSTER_HEIGHT.dp)
                .width(POSTER_WIDTH.dp),
            contentScale = ContentScale.Fit,
        )

        MovieInformation(
            movie = movie,
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp),
        )

        FavoriteIcon(
            isFavorite = isFavorite,
            onToggleFavorite = { onToggleFavorite(movie.id) },
        )
    }
}

@Composable
private fun MovieInformation(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = movie.releaseDate?.year?.toString().orEmpty(),
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodySmall,
        )
        Text(
            text = movie.title.orEmpty(),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        )
        RatingStarsRow(
            rating = movie.rating?.toInt() ?: 0,
        )
    }
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
            MovieListElement(
                isFavorite = false,
                movie = Movie(
                    id = 1,
                    title = "Title",
                    rating = 4.0,
                    revenue = 1000000,
                    releaseDate = Clock.System.now()
                        .toLocalDateTime(TimeZone.currentSystemDefault()).date,
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
                onToggleFavorite = { }
            )
        }
    }
}
