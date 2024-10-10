package at.marki.moviedb.feature.search.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.components.StandardTextField
import at.marki.moviedb.core.designsystems.theme.AppTheme
import kotlinx.coroutines.delay

@Composable
internal fun SearchField(
    initialQueryParameter: String,
    onSearchMovies: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var text by rememberSaveable { mutableStateOf(initialQueryParameter) }
    val keyboardController = LocalSoftwareKeyboardController.current
    var closeKeyboardWithDelay by remember { mutableStateOf(false) }

    LaunchedEffect(closeKeyboardWithDelay) {
        if (closeKeyboardWithDelay) {
            delay(200)
            keyboardController?.hide()
        }
        closeKeyboardWithDelay = false
    }

    StandardTextField(
        value = text,
        onTextChanged = { query ->
            text = query
            onSearchMovies(query)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        label = "Search",
    )
}

@ThemePreviews
@Composable
private fun SearchFieldPreview() {
    AppTheme {
        Surface(color = MaterialTheme.colorScheme.primaryContainer) {
            SearchField(
                initialQueryParameter = "Memento",
                onSearchMovies = {},
            )
        }
    }
}
