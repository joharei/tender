import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

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

ksp {
	arg("KOIN_CONFIG_CHECK", "true")
}

dependencies {
	add("kspCommonMainMetadata", libs.koin.ksp)
}

// WORKAROUND: ADD this dependsOn("kspCommonMainKotlinMetadata") instead of above dependencies
tasks.withType<KotlinCompile<*>>().configureEach {
	if (name != "kspCommonMainKotlinMetadata") {
		dependsOn("kspCommonMainKotlinMetadata")
	}
}
afterEvaluate {
	tasks.filter {
		it.name.contains("SourcesJar", true)
	}.forEach {
		println("SourceJarTask====>${it.name}")
		it.dependsOn("kspCommonMainKotlinMetadata")
	}
}