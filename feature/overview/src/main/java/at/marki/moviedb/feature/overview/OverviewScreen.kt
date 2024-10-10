package at.marki.moviedb.feature.overview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import at.marki.moviedb.core.datastore.User
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.components.ErrorState
import at.marki.moviedb.core.designsystems.components.LoadingState
import at.marki.moviedb.core.designsystems.components.MovieListElement
import at.marki.moviedb.core.designsystems.localCompositions.LocalHorizontalContentPadding
import at.marki.moviedb.core.designsystems.theme.AppTheme
import at.marki.moviedb.feature.details.DetailsBottomSheet
import at.marki.moviedb.feature.overview.ui.FavoritesComponent
import at.marki.moviedb.feature.overview.ui.OverviewAppBar

@Composable
fun OverviewRoute(
    onNavigateToSearch: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    OverviewScreen(
        uiState = uiState.value,
        onNavigateToSearch = onNavigateToSearch,
        onLogout = { viewModel.logout() },
        onToggleFavorite = { movieId -> viewModel.toggleFavorite(movieId) },
        modifier = modifier,
    )
}

@Composable
fun OverviewScreen(
    uiState: OverviewViewModelUiState,
    onNavigateToSearch: () -> Unit,
    onLogout: () -> Unit,
    onToggleFavorite: (movieId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isDetailsBottomSheetVisible by remember { mutableStateOf(false) }
    var selectedMovieId by remember { mutableLongStateOf(-1) }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        when (uiState) {
            OverviewViewModelUiState.Loading -> LoadingState()
            OverviewViewModelUiState.Error -> ErrorState()
            is OverviewViewModelUiState.Success -> SuccessState(
                uiState = uiState,
                onNavigateToSearch = onNavigateToSearch,
                onOpenMovieDetails = { movieId ->
                    selectedMovieId = movieId
                    isDetailsBottomSheetVisible = true
                },
                onToggleFavorite = onToggleFavorite,
                onLogout = onLogout,
            )
        }
    }

    if (isDetailsBottomSheetVisible) {
        DetailsBottomSheet(
            onDismissRequest = { isDetailsBottomSheetVisible = false },
            movieId = selectedMovieId,
        )
    }
}

@Composable
private fun SuccessState(
    uiState: OverviewViewModelUiState.Success,
    onNavigateToSearch: () -> Unit,
    onOpenMovieDetails: (movieId: Long) -> Unit,
    onToggleFavorite: (movieId: Long) -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isLogoutMenuVisible by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            Box(
                modifier = Modifier.padding(bottom = 40.dp),
            ) {
                OverviewAppBar(
                    user = uiState.user,
                    onSearchClick = onNavigateToSearch,
                    onUserClick = {
                        isLogoutMenuVisible = true
                    },
                )
                LogoutMenu(
                    isVisible = isLogoutMenuVisible,
                    onDismissRequest = { isLogoutMenuVisible = false },
                    onLogout = {
                        isLogoutMenuVisible = false
                        onLogout()
                    },
                )
            }
        }
        item {
            AnimatedVisibility(visible = uiState.favorites.isNotEmpty()) {
                Column {
                    Text(
                        text = buildAnnotatedString {
                            append("YOUR ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("FAVORITES")
                            }
                        },
                        modifier = Modifier
                            .padding(horizontal = LocalHorizontalContentPadding.current)
                            .padding(bottom = 14.dp),
                    )
                    FavoritesComponent(
                        favorites = uiState.favorites,
                        onFavoriteClicked = onOpenMovieDetails,
                        modifier = Modifier.padding(bottom = 30.dp),
                    )
                }
            }
        }
        item {
            Text(
                text = buildAnnotatedString {
                    append("OUR ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("STAFF PICKS")
                    }
                },
                modifier = Modifier
                    .padding(horizontal = LocalHorizontalContentPadding.current)
                    .padding(bottom = 10.dp),
            )
        }
        items(uiState.staffRecommendations) { staffRecommendation ->
            val isFavorite = remember(uiState) {
                uiState.favorites.any { it.id == staffRecommendation.id }
            }

            MovieListElement(
                isFavorite = isFavorite,
                movie = staffRecommendation,
                modifier = Modifier
                    .clickable { onOpenMovieDetails(staffRecommendation.id) }
                    .padding(
                        horizontal = LocalHorizontalContentPadding.current,
                        vertical = 10.dp,
                    ),
                onToggleFavorite = onToggleFavorite,
            )

        }
    }
}

@Composable
private fun LogoutMenu(
    isVisible: Boolean,
    onDismissRequest: () -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DropdownMenu(
        expanded = isVisible,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    ) {
        DropdownMenuItem(
            text = { Text("Logout") },
            onClick = {
                onLogout()
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = null
                )
            }
        )
    }
}


@Composable
@ThemePreviews
fun OverviewScreenPreview() {
    AppTheme {
        Surface {
            OverviewScreen(
                uiState = OverviewViewModelUiState.Success(
                    user = User(
                        name = "Max Mustermann",
                        email = "",
                    ),
                    favorites = emptyList(),
                    staffRecommendations = emptyList(),
                ),
                onNavigateToSearch = {},
                onToggleFavorite = {},
                onLogout = {},
            )
        }
    }
}