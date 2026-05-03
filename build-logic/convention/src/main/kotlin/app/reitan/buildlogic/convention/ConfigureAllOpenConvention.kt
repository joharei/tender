package app.reitan.buildlogic.convention

import org.gradle.api.Project
import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension

internal fun Project.configureAllOpen(extension: AllOpenExtension) = extension.apply {
    fun isTestingTask(name: String) = name.contains("test", ignoreCase = true) || "kover" in name

    val isTesting = gradle
        .startParameter
        .taskNames
        .any(::isTestingTask)

    if (isTesting) {
        annotation("app.reitan.testutils.mockable.Mockable")
    }
}
