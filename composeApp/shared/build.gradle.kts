plugins {
	alias(libs.plugins.local.multiplatform.android)
	alias(libs.plugins.local.multiplatform.jvm)
	alias(libs.plugins.compose.multiplatform)
	alias(libs.plugins.compose.compiler)
	alias(libs.plugins.kotlin.serialization)
}

kotlin {
	sourceSets {
		commonMain.dependencies {
			implementation(projects.diBridge)
			implementation(projects.presentation)
			implementation(projects.resources)

			implementation(libs.kotlinx.datetime)
			implementation(libs.kotlinx.serialization.json)

			implementation(libs.koin.compose)
			implementation(libs.koin.compose.viewmodel)
			implementation(libs.koin.compose.viewmodel.navigation)

			implementation(libs.compose.runtime)
			implementation(libs.compose.foundation)
			implementation(libs.compose.material3)
			implementation(libs.compose.material.icons.extended)
			implementation(libs.compose.ui)
			implementation(libs.compose.ui.tooling)
			implementation(libs.compose.ui.tooling.preview)
			implementation(libs.compose.material3.adaptive)

			implementation(libs.lifecycle.runtime.compose)

			implementation(libs.navigation.compose)
			implementation(libs.moko.compose)
		}

		commonTest.dependencies {
			implementation(libs.koin.test)
//			implementation(libs.koin.test.junit4)
		}
	}
}
