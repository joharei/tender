@file:Suppress("UnstableApiUsage")

rootProject.name = "Tender"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
	repositories {
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
		maven("https://www.jetbrains.com/intellij-repository/releases")
		maven("https://packages.jetbrains.team/maven/p/ij/intellij-dependencies")
		maven("https://packages.jetbrains.team/maven/p/amper/amper")
		google()
		gradlePluginPortal()
		mavenCentral()
	}
}

plugins {
	id("org.jetbrains.amper.settings.plugin").version("0.5.0")
}

dependencyResolutionManagement {
	repositories {
		google()
		mavenCentral()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	}
}

include(
	"composeApp:androidApp",
	"composeApp:jvmApp",
	"composeApp:shared",
	"iosShared",
	"data:db",
	"data:network",
	"diBridge",
	"domain",
	"presentation",
	"resources",
)
