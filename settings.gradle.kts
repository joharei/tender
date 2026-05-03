@file:Suppress("UnstableApiUsage")

rootProject.name = "Tender"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
	includeBuild("build-logic")

	repositories {
		google()
		gradlePluginPortal()
		mavenCentral()
	}
}

dependencyResolutionManagement {
	repositories {
		google()
		mavenCentral()
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
