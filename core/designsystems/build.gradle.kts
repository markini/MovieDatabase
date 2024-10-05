plugins {
    alias(libs.plugins.moviedb.android.library)
    alias(libs.plugins.moviedb.android.library.compose)
}

android {
    compileSdk = 34
    namespace = "at.marki.moviedb.core.designsystems"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.lifecycle.viewmodel.compose)
}
