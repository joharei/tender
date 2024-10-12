import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
	alias(libs.plugins.skie)
}

kotlin {
	targets.filterIsInstance<KotlinNativeTarget>().forEach {
		it.binaries {
			framework {
				baseName = "Shared"
				export(project(":presentation"))
				linkerOpts("-lsqlite3")
			}
		}
	}
}

skie {
    features {
        enableSwiftUIObservingPreview = true
    }
}