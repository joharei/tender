import app.reitan.buildlogic.convention.configureAndroidPlatform
import app.reitan.buildlogic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformAndroidConfigurationPlugin : Plugin<Project> {
	override fun apply(target: Project): Unit = with(target) {
		pluginManager.apply("zapappshared.kotlinMultiplatformConfiguration")
		pluginManager.apply(
			libs.findPlugin("android.kotlin.multiplatform.library").get().get().pluginId,
		)

		extensions.configure<KotlinMultiplatformExtension> {
			configureAndroidPlatform(extension = this)
		}
	}
}

