plugins {
	alias(libs.plugins.local.multiplatform.android)
	alias(libs.plugins.local.multiplatform.jvm)
	alias(libs.plugins.local.multiplatform.ios)
	alias(libs.plugins.moko)
}

kotlin {
	sourceSets {
		commonMain.dependencies {
			implementation(libs.moko)
		}
	}

	compilerOptions {
		freeCompilerArgs.add("-Xexpect-actual-classes")
	}
}

multiplatformResources {
	resourcesPackage.set("resources")
}
