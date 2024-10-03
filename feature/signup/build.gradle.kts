plugins {
    alias(libs.plugins.moviedb.android.feature)
    alias(libs.plugins.moviedb.android.library.compose)
}

android {
    compileSdk = 34
    namespace = "at.marki.moviedb.feature.signup"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:designsystems"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
}
