import app.reitan.buildlogic.convention.configureJvmPlatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformJvmConfigurationPlugin : Plugin<Project> {
	override fun apply(target: Project): Unit = with(target) {
		pluginManager.apply("zapappshared.kotlinMultiplatformConfiguration")

		extensions.configure<KotlinMultiplatformExtension> {
			configureJvmPlatform(extension = this)
		}
	}
}

