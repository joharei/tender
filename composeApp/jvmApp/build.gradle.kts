plugins {
	alias(libs.plugins.kotlin.multiplatform)
	alias(libs.plugins.compose.multiplatform)
	alias(libs.plugins.compose.compiler)
}

kotlin {
	val jvmVersion = libs.versions.jvmTarget.get()

	jvm {
		compilerOptions {
			freeCompilerArgs.add("-Xjdk-release=$jvmVersion")
		}
		tasks.withType<JavaCompile> {
			options.release.set(jvmVersion.toInt())
		}
	}

	sourceSets {
		commonMain.dependencies {
			implementation(projects.composeApp.shared)

			implementation(libs.compose.foundation)
			implementation(libs.compose.material3)
			implementation(compose.desktop.currentOs)
			implementation(libs.logback)
		}
	}
}
