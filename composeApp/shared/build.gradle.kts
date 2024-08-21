kotlin {
	sourceSets {
		commonMain.dependencies {
			implementation(compose.components.uiToolingPreview)
		}

		// TODO: this is a workaround in CMP 1.7.0-alpha03. Remove when fixed
		androidMain.dependencies {
			implementation("androidx.window:window-core:1.3.0")
		}
	}
}

// TODO: this is a workaround in CMP 1.7.0-alpha03. Remove when fixed
configurations.configureEach {
	exclude("androidx.window.core", "window-core")
}