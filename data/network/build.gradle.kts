plugins {
	alias(libs.plugins.local.multiplatform.jvm)
	alias(libs.plugins.local.multiplatform.ios)
}

kotlin {
	sourceSets {
		commonMain.dependencies {
			implementation(projects.domain)

			implementation(libs.ktor.client.core)
			implementation(libs.ktor.client.logging)
			implementation(libs.ktor.client.content.negotiation)
			implementation(libs.ktor.serialization.kotlinx.json)
		}
		jvmMain.dependencies {
			implementation(libs.ktor.client.okhttp)
		}
		iosMain.dependencies {
			implementation(libs.ktor.client.darwin)
		}
	}
}
