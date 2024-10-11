import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

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