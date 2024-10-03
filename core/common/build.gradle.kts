plugins {
    alias(libs.plugins.moviedb.android.library)
    alias(libs.plugins.moviedb.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    compileSdk = 34
    namespace = "at.marki.moviedb.core.common"
}

dependencies {
    api(platform(libs.androidx.compose.bom))
}
