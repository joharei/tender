plugins {
	alias(libs.plugins.local.multiplatform.android)
	alias(libs.plugins.local.multiplatform.jvm)
	alias(libs.plugins.local.multiplatform.ios)
}

kotlin {
	sourceSets {
		commonMain.dependencies {
			implementation(projects.domain)

			implementation(projects.data.db)
			implementation(projects.data.network)
		}
	}
}
