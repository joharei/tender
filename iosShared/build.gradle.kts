import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
	alias(libs.plugins.skie)
	alias(libs.plugins.moko)
}

kotlin {
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
}

skie {
    features {
        enableSwiftUIObservingPreview = true
    }
}