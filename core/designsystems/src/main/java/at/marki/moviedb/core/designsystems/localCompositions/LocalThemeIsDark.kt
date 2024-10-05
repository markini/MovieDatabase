package at.marki.moviedb.core.designsystems.localCompositions

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * CompositionLocal containing the information whether the theme is dark or not.
 *
 * Defaults to a guarding false.
 */
val LocalThemeIsDark = staticCompositionLocalOf { false }
