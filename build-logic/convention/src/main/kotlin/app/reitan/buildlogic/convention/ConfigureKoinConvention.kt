package app.reitan.buildlogic.convention

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKoin(
    extension: KotlinMultiplatformExtension,
) = extension.apply {
//    with(pluginManager) {
//        apply(libs.findPlugin("koin-compiler").get().get().pluginId)
//    }

    sourceSets.apply {
        commonMain {
            dependencies {
                implementation(libs.findLibrary("koin.core").get())
//                implementation(libs.findLibrary("koin.annotations").get())
            }
        }
    }
}
