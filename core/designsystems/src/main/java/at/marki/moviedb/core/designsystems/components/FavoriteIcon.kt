package at.marki.moviedb.core.designsystems.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import at.marki.moviedb.core.designsystems.theme.favoriteColor

@Composable
fun FavoriteIcon(
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = if (isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
        contentDescription = null,
        tint = if (isFavorite) favoriteColor else LocalContentColor.current,
        modifier = modifier
            .clip(CircleShape)
            .clickable { onToggleFavorite() },
    )
}
