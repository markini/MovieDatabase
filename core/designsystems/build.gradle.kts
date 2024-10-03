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

    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.util)
}
