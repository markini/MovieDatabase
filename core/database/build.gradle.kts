plugins {
    alias(libs.plugins.moviedb.android.library)
    alias(libs.plugins.moviedb.android.hilt)
    alias(libs.plugins.moviedb.android.room)
}

android {
    namespace = "at.marki.moviedb.core.database"
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
}
