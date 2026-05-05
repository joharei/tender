plugins {
	alias(libs.plugins.local.multiplatform.android)
	alias(libs.plugins.local.multiplatform.jvm)
	alias(libs.plugins.local.multiplatform.ios)
	alias(libs.plugins.kotlin.serialization)
}

kotlin {
	sourceSets {
		commonMain.dependencies {
			implementation(projects.domain)

			implementation(libs.kotlinx.serialization.json)

			implementation(libs.koin.core.viewmodel)
			implementation(libs.lifecycle.viewmodel)
			implementation(libs.lifecycle.viewmodel.savedstate)
			implementation(libs.navigation.compose)
		}

		val jvmAndAndroid by creating {
			dependsOn(commonMain.get())
		}

		jvmMain.dependencies {
			implementation(libs.kotlinx.coroutines.swing)
		}
		jvmMain {
			dependsOn(jvmAndAndroid)
		}
		androidMain {
			dependsOn(jvmAndAndroid)
		}

	}

	compilerOptions {
		freeCompilerArgs.add("-Xexpect-actual-classes")
	}
}
