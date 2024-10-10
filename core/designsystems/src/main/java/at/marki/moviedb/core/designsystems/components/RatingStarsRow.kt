package at.marki.moviedb.core.designsystems.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import at.marki.moviedb.core.designsystems.theme.favoriteColor

private const val MAX_RATING = 5
private const val STAR_SIZE = 10

@Composable
fun RatingStarsRow(
    rating: Int,
    modifier: Modifier = Modifier,
    starSize: Int = STAR_SIZE,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (star in 1..MAX_RATING) {
            val isSelected = star <= rating

            RatingStar(
                isSelected = isSelected,
                modifier = Modifier.size(starSize.dp),
            )
        }
    }
}

@Composable
private fun RatingStar(
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    val iconRes = when (isSelected) {
        true -> Icons.Default.Star
        false -> Icons.Default.StarOutline
    }

    Icon(
        imageVector = iconRes,
        contentDescription = null,
        modifier = modifier,
        tint = if (isSelected) favoriteColor else Color.Gray,
    )
}
