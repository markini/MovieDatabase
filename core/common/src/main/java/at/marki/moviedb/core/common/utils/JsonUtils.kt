package at.marki.moviedb.core.common.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import timber.log.Timber

object JsonUtils {

    /**
     * Encodes an object to json and catches any exception that might occur
     *
     * @return the json string or null if an exception occurred
     */
    inline fun <reified T> encodeJson(objectToEncode: T): String? {
        return try {
            Json.encodeToString(objectToEncode)
        } catch (exception: Exception) {
            Timber.e(exception, "Could not encode json")
            null
        }
    }

    /**
     * Decodes a json string to an object and catches any exception that might occur
     *
     * @return the object or null if an exception occurred
     */
    inline fun <reified T> decodeJson(value: String?): T? {
        return try {
            if (value == null) return null
            Json.decodeFromString<T>(value)
        } catch (exception: Exception) {
            Timber.e(exception, "Could not decode json")
            null
        }
    }
}
