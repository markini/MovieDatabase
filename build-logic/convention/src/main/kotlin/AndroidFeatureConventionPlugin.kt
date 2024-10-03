import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("moviedb.android.library")
                apply("moviedb.android.hilt")
                apply("androidx.navigation.safeargs.kotlin")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            extensions.configure<LibraryExtension> {
                buildFeatures {
                    dataBinding = true
                }
            }

            dependencies {
                add("implementation", project(":core:common"))
//                add("implementation", project(":core:data"))
//                add("implementation", project(":core:datastore"))
                add("implementation", project(":core:designsystems"))
                add("implementation", project(":core:localization"))
//                add("implementation", project(":core:model"))

                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add(
                    "implementation",
                    libs.findLibrary("androidx.lifecycle.viewmodel.compose").get()
                )

                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
            }
        }
    }
}
