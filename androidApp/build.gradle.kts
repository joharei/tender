import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
	alias(libs.plugins.ksp)
}

android {
	sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
	sourceSets["main"].res.srcDirs("src/androidMain/res")
	sourceSets["main"].resources.srcDirs("src/commonMain/resources")

	defaultConfig {
		versionCode = 1
		versionName = "1.0"
	}

	applicationVariants.all {
		val variantName = name
		sourceSets {
			getByName("main") {
				val srcDir = "build/generated/ksp/android/android${variantName.capitalized()}/kotlin"
				java.srcDir(srcDir)
			}
		}
	}

	dependencies {
		add("kspAndroid", libs.koin.ksp)
	}
}

tasks.withType<KotlinCompilationTask<*>>().configureEach {
	val buildType = if ("Debug" in name) "Debug" else "Release"
	val kspTask = "ksp${buildType}KotlinAndroid"
	if (name != kspTask) {
		dependsOn(kspTask)
	}
}