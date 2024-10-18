package at.marki.moviedb.core.designsystems.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.theme.AppTheme
import at.marki.moviedb.core.designsystems.theme.favoriteColor

@Composable
fun FavoriteIcon(
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onToggleFavorite,
        modifier = modifier,
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
            contentDescription = null,
            tint = if (isFavorite) favoriteColor else LocalContentColor.current,
        )
    }
}

@ThemePreviews
@Composable
private fun FavoriteIconPreview() {
    AppTheme {
        Surface {
            FavoriteIcon(
                isFavorite = true,
                onToggleFavorite = {},
            )
        }
    }
}

@ThemePreviews
@Composable
private fun FavoriteIconNoFavoritePreview() {
    AppTheme {
        Surface {
            FavoriteIcon(
                isFavorite = false,
                onToggleFavorite = {},
            )
        }
    }
}
