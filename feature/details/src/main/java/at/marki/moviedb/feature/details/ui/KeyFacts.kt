package at.marki.moviedb.feature.details.ui

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
internal fun KeyFacts(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        KeyFactsTitle()
        Spacer(modifier = Modifier.height(10.dp))
        KeyFactsRow(movie = movie)
    }
}

@Composable
private fun KeyFactsTitle(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Key Facts",
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
private fun KeyFactsRow(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = spacedBy(10.dp),
    ) {
        KeyFactsColumn(
            facts = getFirstFactsPair(movie),
            modifier = Modifier.weight(1f),
        )
        KeyFactsColumn(
            facts = getSecondFactsPair(movie),
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun KeyFactsColumn(
    facts: List<Pair<String, String>>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = spacedBy(10.dp),
    ) {
        facts.forEach { (headline, text) ->
            KeyFactChip(
                headline = headline,
                text = text,
            )
        }
    }
}

@Composable
internal fun KeyFactChip(
    headline: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            KeyFactHeadline(text = headline)
            KeyFactText(text = text)
        }
    }
}

@Composable
private fun KeyFactHeadline(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
private fun KeyFactText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground,
    )
}

private fun getFirstFactsPair(movie: Movie): List<Pair<String, String>> {
    return listOf(
        "Budget" to movie.budget?.toString().orEmpty(),
        "Original Language" to movie.language.orEmpty(),
    )
}

private fun getSecondFactsPair(movie: Movie): List<Pair<String, String>> {
    return listOf(
        "Revenue" to movie.revenue?.toString().orEmpty(),
        "Rating" to movie.rating?.toInt()?.toString().orEmpty(),
    )
}

@ThemePreviews
@Composable
private fun MovieHeadlineInformationPreview() {
    AppTheme {
        Surface {
            KeyFacts(
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
