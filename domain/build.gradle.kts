import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
	kotlin("multiplatform")
	alias(libs.plugins.ksp)
}

kotlin {
	sourceSets {
		val commonMain by getting {
			kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
		}
	}
}

dependencies {
	add("kspCommonMainMetadata", libs.koin.ksp)
	configurations
		.filter { it.name.startsWith("ksp") && it.name.contains("Test") }
		.forEach {
			add(it.name, libs.mockative.processor)
		}
}

// WORKAROUND: ADD this dependsOn("kspCommonMainKotlinMetadata") instead of above dependencies
tasks.withType<KotlinCompilationTask<*>>().configureEach {
	if (name != "kspCommonMainKotlinMetadata") {
		dependsOn("kspCommonMainKotlinMetadata")
	}
}
afterEvaluate {
	tasks.filter {
		it.name.contains("SourcesJar", true)
	}.forEach {
		it.dependsOn("kspCommonMainKotlinMetadata")
	}
}