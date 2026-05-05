import app.reitan.buildlogic.convention.configureAllOpen
import app.reitan.buildlogic.convention.configureKoin
import app.reitan.buildlogic.convention.configureKotlinMultiplatform
import app.reitan.buildlogic.convention.configureMokkery
import app.reitan.buildlogic.convention.libs
import dev.mokkery.gradle.MokkeryGradleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformConfigurationPlugin : Plugin<Project> {
	override fun apply(target: Project): Unit = with(target) {
		val groupPath = path.trimStart(':').replace(':', '.')
		group = "app.reitan.$groupPath"
		version = project.findProperty("versionName") as String

		with(pluginManager) {
			apply(libs.findPlugin("kotlin.multiplatform").get().get().pluginId)
			apply(libs.findPlugin("kover").get().get().pluginId)
			apply(libs.findPlugin("mokkery").get().get().pluginId)
			apply(libs.findPlugin("allopen").get().get().pluginId)
		}

		extensions.configure<KotlinMultiplatformExtension> {
			configureKotlinMultiplatform(extension = this)
			configureKoin(extension = this)
		}

		extensions.configure<MokkeryGradleExtension>(::configureMokkery)
		extensions.configure<AllOpenExtension>(::configureAllOpen)
	}
}
