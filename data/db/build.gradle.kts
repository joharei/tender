@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
	alias(libs.plugins.local.multiplatform.android)
	alias(libs.plugins.local.multiplatform.jvm)
	alias(libs.plugins.local.multiplatform.ios)
	alias(libs.plugins.sqldelight)
}

kotlin {
	sourceSets {
		commonMain.dependencies {
			implementation(projects.domain)

			implementation(libs.kotlinx.datetime)
			implementation(libs.sqldelight.runtime)
			implementation(libs.sqldelight.coroutines)
			implementation(libs.sqldelight.primitive.adapters)
		}

		jvmMain.dependencies {
			implementation(libs.sqldelight.jvm.driver)
		}

		androidMain.dependencies {
			implementation(libs.sqldelight.android.driver)
		}

		iosMain.dependencies {
			implementation(libs.sqldelight.native.driver)
		}
	}

	compilerOptions {
		freeCompilerArgs.add("-Xexpect-actual-classes")
	}
}

sqldelight {
	databases {
		create("Database") {
			packageName = "db"
			srcDirs.setFrom("sqldelight")
		}
	}
}
