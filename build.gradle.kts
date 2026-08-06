import dev.detekt.gradle.Detekt

plugins {
	alias(libs.plugins.kotlin.multiplatform) apply false
	alias(libs.plugins.kotlin.serialization) apply false
	alias(libs.plugins.android.application) apply false
	alias(libs.plugins.android.kotlin.multiplatform.library) apply false
	alias(libs.plugins.compose.multiplatform) apply false
	alias(libs.plugins.compose.compiler) apply false
	alias(libs.plugins.sqldelight) apply false
	alias(libs.plugins.skie) apply false
	alias(libs.plugins.moko) apply false
	alias(libs.plugins.mokkery) apply false
	alias(libs.plugins.allopen) apply false
	alias(libs.plugins.kover) apply false
	alias(libs.plugins.local.multiplatform.android) apply false
	alias(libs.plugins.local.multiplatform.jvm) apply false
	alias(libs.plugins.local.multiplatform.ios) apply false
	alias(libs.plugins.detekt)
}

dependencies {
	detektPlugins(libs.detekt.ktlint)

//	gradle.projectsEvaluated {
//		subprojects.forEach {
//			if (it.plugins.hasPlugin(libs.plugins.kover.get().pluginId)) {
//				kover(it)
//			}
//		}
//	}
}

//kover {
//	reports {
//		filters {
//			excludes {
//				annotatedBy("org.koin.core.annotation.Module")
//			}
//		}
//	}
//}

tasks.withType<Detekt>().configureEach {
	reports {
		checkstyle.required.set(true)
		html.required.set(false)
		sarif.required.set(false)
		markdown.required.set(false)
	}
}

tasks.register<Detekt>("detektAll") {
	autoCorrect = project.hasProperty("detektAutoCorrect")
	parallel = true
	setSource(files(projectDir))
	include("**/*.kt")
	include("**/*.kts")
	exclude("**/resources/**")
	exclude("**/build/**")
	config = files("$rootDir/config/detekt/detekt.yml")
	buildUponDefaultConfig = true
}
