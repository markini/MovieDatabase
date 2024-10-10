package at.marki.moviedb.feature.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.theme.AppTheme
import at.marki.moviedb.core.model.Movie
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

@Composable
internal fun MovieHeadlineInformation(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MovieDate(movie = movie)
        Spacer(modifier = Modifier.height(10.dp))
        MovieTitle(movie = movie)
        Spacer(modifier = Modifier.height(8.dp))
        Genres(movie = movie)
    }
}

@Composable
private fun MovieDate(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = movie.releaseDate?.let { formatLocalDate(it) }.orEmpty(),
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = ".",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 6.dp),
        )
        Text(
            text = movie.runtime?.formatRuntime().orEmpty(),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

fun formatLocalDate(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return date.toJavaLocalDate().format(formatter)
}

fun Int.formatRuntime(): String {
    val hours = this / 60
    val minutes = this % 60
    return "$hours h $minutes min"
}

@Composable
private fun MovieTitle(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(movie.title.orEmpty())
            }
            append(" (${movie.releaseDate?.year})")
        },
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        fontSize = 30.sp,
        textAlign = TextAlign.Center,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun Genres(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = spacedBy(8.dp),
    ) {
        movie.genres.forEach {
            GenreChip(
                text = it,
            )
        }
    }
}

@Composable
internal fun GenreChip(
    text: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF0F1F1),
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
    ) {
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp),
        )
    }
}

@ThemePreviews
@Composable
private fun MovieHeadlineInformationPreview() {
    AppTheme {
        Surface {
            MovieHeadlineInformation(
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
