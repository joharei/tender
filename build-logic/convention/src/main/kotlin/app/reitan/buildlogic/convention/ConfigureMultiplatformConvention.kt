package app.reitan.buildlogic.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinMultiplatform(
	extension: KotlinMultiplatformExtension,
) = extension.apply {
	compilerOptions {
		freeCompilerArgs.addAll(
			"-Xexpect-actual-classes",
		)
	}

	tasks.withType<KotlinCompile> {
		if (!name.contains("Test", ignoreCase = true)) {
			this@apply.explicitApi()
		}
	}

	sourceSets.apply {
		commonMain.dependencies {
			implementation(libs.findBundle("common").get())
//			if (project.path != ":testUtils:mockable") {
//				implementation(project(":testUtils:mockable"))
//			}
//			implementation(libs.findLibrary("kermit").get())
		}

		commonTest.dependencies {
			implementation(kotlin("test"))
			implementation(libs.findLibrary("kotlinx.coroutines.test").get())
		}

		all {
			languageSettings {
				optIn("kotlin.uuid.ExperimentalUuidApi")
				optIn("kotlin.experimental.ExperimentalObjCName")
			}
		}
	}
}
