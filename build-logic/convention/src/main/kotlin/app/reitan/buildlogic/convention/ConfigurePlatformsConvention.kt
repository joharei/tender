package app.reitan.buildlogic.convention

import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureAndroidPlatform(
	extension: KotlinMultiplatformExtension,
) {
	with(extension) {
		targets.android()?.apply {
			namespace = "$group.$name"
			compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
			minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()
			androidResources.enable = false

			withHostTest {
				isIncludeAndroidResources = true
			}

			packaging {
				resources {
					excludes += "/META-INF/{AL2.0,LGPL2.1}"
				}
			}

			compilerOptions {
				val jvmVersion = libs.findVersion("jvmTarget").get().requiredVersion
				freeCompilerArgs.add("-Xjdk-release=$jvmVersion")
			}
		}
	}
}

internal fun Project.configureJvmPlatform(
	extension: KotlinMultiplatformExtension,
) {
	with(extension) {
		val jvmVersion = libs.findVersion("jvmTarget").get().requiredVersion

		jvm {
			this.compilerOptions {
				freeCompilerArgs.add("-Xjdk-release=$jvmVersion")
			}
			this@configureJvmPlatform.tasks.withType<JavaCompile> {
				this.options.release.set(jvmVersion.toInt())
			}
		}
	}
}

internal fun Project.configureIosPlatforms(
	extension: KotlinMultiplatformExtension,
) {
	with(extension) {
		iosArm64()
		iosSimulatorArm64()
		applyDefaultHierarchyTemplate()
	}
}
