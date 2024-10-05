package at.marki.moviedb.core.designsystems

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

internal const val API_LEVEL = 33

@Preview(
    name = "Light",
    group = "Theme",
    apiLevel = API_LEVEL,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark",
    group = "Theme",
    apiLevel = API_LEVEL,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class ThemePreviews
