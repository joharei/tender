import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
	alias(libs.plugins.ksp)
}

kotlin {
	sourceSets {
		val commonMain by getting {
			kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
		}
		
		commonMain.dependencies {
			implementation(compose.components.uiToolingPreview)
		}
	}
}

configurations.commonMainApi {
	exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-android")
}

ksp {
	arg("KOIN_CONFIG_CHECK", "true")
}

dependencies {
	add("kspCommonMainMetadata", libs.koin.ksp)
//	add("kspJvm", libs.koin.ksp)
//	add("kspJvmTest", libs.koin.ksp)
}

//// WORKAROUND: ADD this dependsOn("kspCommonMainKotlinMetadata") instead of above dependencies
//tasks.withType<KotlinCompilationTask<*>>().configureEach {
//	println("KotlinCompilationTask: $name")
//	if (name != "kspCommonMainKotlinMetadata") {
//		dependsOn("kspCommonMainKotlinMetadata")
//	}
//}
//afterEvaluate {
//	tasks.filter {
//		it.name.contains("SourcesJar", true)
//	}.forEach {
//		println("SourceJarTask: ${it.name}")
//		it.dependsOn("kspCommonMainKotlinMetadata")
//	}
//}