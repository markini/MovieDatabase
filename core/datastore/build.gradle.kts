plugins {
    alias(libs.plugins.moviedb.android.library)
    alias(libs.plugins.moviedb.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "at.marki.moviedb.core.datastore"
}

dependencies {
    implementation(project(":core:common"))
    //implementation(projects.core.model)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.dataStore.core)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
}

