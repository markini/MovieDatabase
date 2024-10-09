package at.marki.moviedb.feature.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.localCompositions.LocalHorizontalContentPadding
import at.marki.moviedb.core.model.Movie
import coil.compose.SubcomposeAsyncImage
import coil.imageLoader
import coil.request.ImageRequest

private const val POSTER_HEIGHT = 270
private const val POSTER_WIDTH = 182

@Composable
internal fun FavoritesComponent(
    favorites: List<Movie>,
    onFavoriteClicked: (movieId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = LocalHorizontalContentPadding.current),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(favorites) { movie ->
            FavoriteElement(
                movie = movie,
                onFavoriteClicked = onFavoriteClicked,
                modifier = Modifier.shadow(12.dp, Shapes().medium),
            )
        }
    }
}

@Composable
private fun FavoriteElement(
    movie: Movie,
    onFavoriteClicked: (movieId: Long) -> Unit,
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
            .height(POSTER_HEIGHT.dp)
            .width(POSTER_WIDTH.dp)
            .clip(Shapes().medium)
            .clickable { onFavoriteClicked(movie.id) },
        contentScale = ContentScale.Fit,
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
private fun FavoritesComponentPreview() {

}
