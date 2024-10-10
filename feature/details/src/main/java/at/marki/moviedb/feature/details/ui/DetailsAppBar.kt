package at.marki.moviedb.feature.details.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.components.FavoriteIcon
import at.marki.moviedb.core.designsystems.theme.AppTheme

private const val DETAIL_APP_BAR_HEIGHT = 80

@Composable
internal fun DetailAppBar(
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(DETAIL_APP_BAR_HEIGHT.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        FavoriteIcon(
            isFavorite = isFavorite,
            onToggleFavorite = onToggleFavorite,
            modifier = Modifier.padding(end = 16.dp),
        )
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onDismiss() },
        )
    }
}

@ThemePreviews
@Composable
private fun SettingsScreenPreview() {
    AppTheme {
        Surface {
            DetailAppBar(
                isFavorite = true,
                onToggleFavorite = {},
                onDismiss = {},
            )
        }
    }
}
