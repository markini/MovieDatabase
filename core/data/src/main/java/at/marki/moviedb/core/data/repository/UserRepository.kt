package at.marki.moviedb.core.data.repository

import at.marki.moviedb.core.datastore.PreferencesDataSource
import at.marki.moviedb.core.datastore.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
) {

    fun isUserLoggedIn(): Flow<Boolean> {
        return preferencesDataSource.user().map { it != null && it.email.isNotEmpty() }
    }

    fun getUser(): Flow<User?> {
        return preferencesDataSource.user()
    }

    suspend fun setUser(user: User) {
        preferencesDataSource.setUser(user)
    }

    suspend fun clearUser() {
        preferencesDataSource.removeUser()
    }
}
