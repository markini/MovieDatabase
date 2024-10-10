package at.marki.moviedb.feature.details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.theme.AppTheme
import at.marki.moviedb.core.model.Movie
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
internal fun DetailsText(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        OverviewTitle()
        Spacer(modifier = Modifier.height(20.dp))
        OverviewText(movie = movie)
    }
}

@Composable
private fun OverviewTitle(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Overview",
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
        ),
        fontSize = 20.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun OverviewText(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Text(
        text = movie.overview.orEmpty(),
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
    )
}

@ThemePreviews
@Composable
private fun MovieHeadlineInformationPreview() {
    AppTheme {
        Surface {
            DetailsText(
                movie = Movie(
                    id = 1,
                    title = "Demolition Man",
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
            )
        }
    }
}
