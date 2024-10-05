package at.marki.moviedb.core.data.repository

import at.marki.moviedb.core.datastore.PreferencesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
) {
    fun isUserLoggedIn(): Flow<Boolean> {
        return preferencesDataSource.isUserLoggedIn()
    }

    suspend fun setUserLoggedIn(isUserLoggedIn: Boolean) {
        preferencesDataSource.setUserLoggedIn(isUserLoggedIn = isUserLoggedIn)
    }
}
