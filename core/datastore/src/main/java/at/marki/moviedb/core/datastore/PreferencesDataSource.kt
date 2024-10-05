package at.marki.moviedb.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<Preferences>,
) {
    suspend fun setLastAppVersion(lastAppVersion: String) = setPreference(
        key = PreferenceItems.lastVersionItem.key,
        value = lastAppVersion,
    )

    suspend fun lastAppVersion(): String? =
        getPreference(PreferenceItems.lastVersionItem.key)

    suspend fun setUserLoggedIn(isUserLoggedIn: Boolean) = setPreference(
        key = PreferenceItems.isUserLoggedIn.key,
        value = isUserLoggedIn,
    )

    fun isUserLoggedIn(): Flow<Boolean> =
        getBooleanPreferenceFlow(PreferenceItems.isUserLoggedIn)

    // Region Helper Functions

    private suspend inline fun <reified T> setJsonPreference(
        jsonPreferenceItem: JsonPreferenceItem<T>,
        value: T,
    ) {
        try {
            setPreference(jsonPreferenceItem.key, Json.encodeToString(value))
        } catch (exception: Exception) {
            Timber.e(exception, "Could not set json preference")
        }
    }

    private suspend fun <T> setPreference(
        key: Preferences.Key<T>,
        value: T,
    ) {
        try {
            userPreferences.edit { preferences ->
                preferences[key] = value
            }
        } catch (exception: Exception) {
            Timber.e(exception, "Could not set preference")
        }
    }

    private suspend fun <T> getPreference(key: Preferences.Key<T>): T? = try {
        getPreferenceFlow(key).firstOrNull()
    } catch (exception: Exception) {
        Timber.e(exception, "Could not retrieve boolean preference")
        null
    }

    private fun <T> getPreferenceFlow(key: Preferences.Key<T>): Flow<T?> = try {
        userPreferences.data.map { preferences ->
            preferences[key]
        }
    } catch (exception: Exception) {
        Timber.e(exception, "Could not retrieve preference")
        flowOf(null)
    }

    private fun getBooleanPreferenceFlow(booleanPreferenceItem: BooleanPreferenceItem) =
        getPreferenceFlow(booleanPreferenceItem.key)
            .map { value ->
                when (value) {
                    null -> booleanPreferenceItem.defaultValue
                    else -> value
                }
            }

    private fun getIntPreferenceFlow(intPreferenceItem: IntPreferenceItem) =
        getPreferenceFlow(intPreferenceItem.key)
            .map { value ->
                when (value) {
                    null -> intPreferenceItem.defaultValue
                    else -> value
                }
            }

    private suspend fun getLongPreference(longPreferenceItem: LongPreferenceItem) =
        getLongPreferenceFlow(longPreferenceItem).firstOrNull() ?: longPreferenceItem.defaultValue

    private fun getLongPreferenceFlow(longPreferenceItem: LongPreferenceItem) =
        getPreferenceFlow(longPreferenceItem.key)
            .map { value ->
                when (value) {
                    null -> longPreferenceItem.defaultValue
                    else -> value
                }
            }

    private fun getStringPreferenceFlow(stringPreferenceItem: StringPreferenceItem) =
        getPreferenceFlow(stringPreferenceItem.key)
            .map { value ->
                when (value) {
                    null -> stringPreferenceItem.defaultValue
                    else -> value
                }
            }

    private inline fun <reified T : Enum<T>> getEnumPreferenceFlow(enumPreferenceItem: EnumPreferenceItem<T>): Flow<T> =
        try {
            userPreferences.data.map { preferences ->
                val name = preferences[enumPreferenceItem.key]
                enumPreferenceItem.enumClass.enumConstants?.find { it.name == name }
                    ?: enumPreferenceItem.defaultValue
            }
        } catch (exception: Exception) {
            Timber.e(exception, "Could not retrieve boolean preference")
            flowOf(enumPreferenceItem.defaultValue)
        }

    // endregion Helper Functions
}
