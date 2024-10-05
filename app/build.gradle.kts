plugins {
    alias(libs.plugins.moviedb.android.application)
    alias(libs.plugins.moviedb.android.application.compose)
    alias(libs.plugins.moviedb.android.hilt)
    alias(libs.plugins.kotlin.serialization)
    kotlin("kapt")
    id("kotlin-parcelize")
    id("idea")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "at.marki.moviedb"

    defaultConfig {
        manifestPlaceholders += mapOf()
        applicationId = "at.marki.moviedb"
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn",
            // Enable experimental coroutines APIs, including Flow
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=kotlin.Experimental",
        )
    }

    androidResources {
        generateLocaleConfig = true
    }
}

dependencies {
    implementation(project(":core:designsystems"))
    implementation(project(":core:localization"))

    implementation(project(":feature:signup"))

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.compose.material3.windowSizeClass)

    implementation(libs.coil.kt)

    implementation(libs.android.inappupdate)
    implementation(libs.semver4j)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.timber)

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.compose)
    implementation(libs.androidx.startup)
    implementation(libs.androidx.material3)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.util)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.work)

    implementation(libs.hilt.ext.work)
    ksp(libs.hilt.ext.compiler)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.config)
    implementation(libs.firebase.messaging)
    implementation(libs.amplitude.sdk)


    implementation(libs.slf4j.api)

    implementation(libs.retrofit.core)
}

kapt {
    correctErrorTypes = true
}

ksp {
    arg("room.generateKotlin", "true")
}

idea {
    module {
        excludeDirs = excludeDirs + file("src/main/baseline-prof.txt")
    }
}
