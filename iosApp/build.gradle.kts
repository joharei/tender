import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

apple {
	dependencies {
		iosAppImplementation(project(":presentation"))
	}
}

fun Project.linkSqlite() {
	val extension = project.extensions.findByType(KotlinMultiplatformExtension::class.java) ?: return
	extension.targets
		.filterIsInstance<KotlinNativeTarget>()
		.flatMap { it.binaries }
		.forEach { compilationUnit ->
			compilationUnit.linkerOpts("-lsqlite3")
		}
}

linkSqlite()