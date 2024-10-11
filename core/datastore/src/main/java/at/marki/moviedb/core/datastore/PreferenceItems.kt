package at.marki.moviedb.core.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * Single source of truth of all user preference data store keys and default values
 */
object PreferenceItems {

    // ---------------------------------------------------------------------------------------------
    // User Keys

    val user by lazy {
        createJsonPreferenceItem(
            key = stringPreferencesKey("key_user"),
            defaultValue = User("", ""),
        )
    }

    // ---------------------------------------------------------------------------------------------
    // Favorites Keys

    val favoriteIdsItem by lazy {
        createJsonPreferenceItem(
            key = stringPreferencesKey("key_favorites"),
            defaultValue = emptyList<String>()
        )
    }

    inline fun <reified T : Enum<T>> createEnumPreferenceItem(
        key: Preferences.Key<String>,
        defaultValue: T,
    ) = EnumPreferenceItem(
        key = key,
        defaultValue = defaultValue,
        enumClass = T::class.java,
    )

    inline fun <reified T> createJsonPreferenceItem(
        key: Preferences.Key<String>,
        defaultValue: T,
    ) = JsonPreferenceItem(
        key = key,
        defaultValue = defaultValue,
        jsonClass = T::class.java,
    )
}


data class BooleanPreferenceItem(
    val key: Preferences.Key<Boolean>,
    val defaultValue: Boolean,
)

data class LongPreferenceItem(
    val key: Preferences.Key<Long>,
    val defaultValue: Long,
)

data class IntPreferenceItem(
    val key: Preferences.Key<Int>,
    val defaultValue: Int,
)

data class StringPreferenceItem(
    val key: Preferences.Key<String>,
    val defaultValue: String,
)

data class EnumPreferenceItem<E : Enum<E>>(
    val key: Preferences.Key<String>,
    val defaultValue: E,
    val enumClass: Class<E>,
)

data class JsonPreferenceItem<T>(
    val key: Preferences.Key<String>,
    val defaultValue: T,
    val jsonClass: Class<T>,
)
