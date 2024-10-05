package at.marki.moviedb

import android.app.Application
import android.util.Log
import at.marki.moviedb.core.data.repository.UserRepository
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MovieDbApplication : Application(), ImageLoaderFactory {

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            setLastAppVersion()
        }
    }

    private suspend fun setLastAppVersion() {
        userRepository.setLastAppVersion(BuildConfig.VERSION_CODE.toString())
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .diskCache {
                DiskCache.Builder()
                    .directory(filesDir.resolve("image_cache")) // default coil cache directory; has to be set
                    .maxSizePercent(0.05) // use 5% of available disk space
                    .build()
            }
            .apply {
                if (BuildConfig.DEBUG) {
                    logger(DebugLogger(Log.VERBOSE))
                }
            }
            .build()
    }
}
