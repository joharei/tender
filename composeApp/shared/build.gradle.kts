kotlin {
	sourceSets {
		commonMain.dependencies {
			implementation(compose.components.uiToolingPreview)
		}
	}
}


compose.resources {
	publicResClass = true
	generateResClass = always
}