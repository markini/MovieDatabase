package at.marki.moviedb.core.data.repository

import at.marki.moviedb.core.datastore.PreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class FavoritesRepository @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
) {
    fun getFavoriteIds(): Flow<List<Long>> {
        return preferencesDataSource.favoriteIdsFlow()
    }

    suspend fun setFavoriteIds(favoriteIds: List<Long>) {
        preferencesDataSource.setFavoriteIds(favoriteIds)
    }

    suspend fun toggleFavoriteId(id: Long) {
        val isCurrentlyFavorite = preferencesDataSource.favoriteIdsFlow().first().contains(id)

        when (isCurrentlyFavorite) {
            true -> preferencesDataSource.removeFavoriteId(id)
            false -> preferencesDataSource.addFavoriteId(id)
        }
    }

    suspend fun addFavoriteId(favoriteId: Long) {
        preferencesDataSource.addFavoriteId(favoriteId)
    }

    suspend fun removeFavoriteId(favoriteId: Long) {
        preferencesDataSource.removeFavoriteId(favoriteId)
    }
}
