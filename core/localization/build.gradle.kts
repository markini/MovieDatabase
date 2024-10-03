plugins {
    id("moviedb.android.library")
}

android {
    compileSdk = 34
    namespace = "at.marki.moviedb.core.localization"

    lint {
        disable += "MissingQuantity"
    }
}

dependencies {
    implementation(libs.kotlinx.datetime)
}
