package at.marki.moviedb.core.data.repository

import at.marki.moviedb.core.datastore.PreferencesDataSource
import at.marki.moviedb.core.datastore.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    suspend fun addFavoriteId(favoriteId: Long) {
        preferencesDataSource.addFavoriteId(favoriteId)
    }

    suspend fun removeFavoriteId(favoriteId: Long) {
        preferencesDataSource.removeFavoriteId(favoriteId)
    }
}
