@file:Suppress("UnstableApiUsage")

rootProject.name = "Tender"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/amper/amper")
        maven("https://www.jetbrains.com/intellij-repository/releases")
        maven("https://packages.jetbrains.team/maven/p/ij/intellij-dependencies")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.jetbrains.amper.settings.plugin").version("0.3.0")
////                                      # available:"0.3.1-dev-580")
////                                      # available:"0.3.1-dev-581")
    id("de.fayard.refreshVersions") version "0.60.5"
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

refreshVersions {
    rejectVersionIf { candidate.stabilityLevel.isLessStableThan(current.stabilityLevel) }
}