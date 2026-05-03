import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
	alias(libs.plugins.kotlin.multiplatform)
	alias(libs.plugins.skie)
	alias(libs.plugins.moko)
}

kotlin {
	iosArm64()
	iosSimulatorArm64()

	targets.filterIsInstance<KotlinNativeTarget>().forEach {
		it.binaries {
			framework {
				baseName = "Shared"
				export(project(":presentation"))
				export(project(":resources"))
				export(libs.moko)
				linkerOpts("-lsqlite3")
				isStatic = false
			}
		}
	}

	sourceSets {
		commonMain.dependencies {
			implementation(projects.diBridge)
			api(projects.presentation)
			api(projects.resources)
			api(libs.moko)
		}
	}
}

skie {
    features {
        enableSwiftUIObservingPreview = true
    }
}
