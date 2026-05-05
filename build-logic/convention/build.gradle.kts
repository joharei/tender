plugins {
    `kotlin-dsl`
}

group = "app.reitan.buildlogic.convention"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.kotlinx.kover.gradlePlugin)
    compileOnly(libs.mokkery.gradlePlugin)
    compileOnly(libs.allopen.gradlePlugin)
    compileOnly(libs.koin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kotlinMultiplatformConfiguration") {
            id = "zapappshared.kotlinMultiplatformConfiguration"
            implementationClass = "KotlinMultiplatformConfigurationPlugin"
        }
        register("kotlinMultiplatformAndroidConfiguration") {
            id = "zapappshared.kotlinMultiplatformAndroidConfiguration"
            implementationClass = "KotlinMultiplatformAndroidConfigurationPlugin"
        }
        register("kotlinMultiplatformJvmConfiguration") {
            id = "zapappshared.kotlinMultiplatformJvmConfiguration"
            implementationClass = "KotlinMultiplatformJvmConfigurationPlugin"
        }
        register("kotlinMultiplatformIosConfiguration") {
            id = "zapappshared.kotlinMultiplatformIosConfiguration"
            implementationClass = "KotlinMultiplatformIosConfigurationPlugin"
        }
    }
}

kotlin {
    val jvmVersion = libs.versions.jvmTarget.get()
    compilerOptions {
        freeCompilerArgs.addAll(
            "-Xjdk-release=$jvmVersion",
        )
    }
    java {
        sourceCompatibility = JavaVersion.toVersion(jvmVersion)
        targetCompatibility = JavaVersion.toVersion(jvmVersion)
    }
}
