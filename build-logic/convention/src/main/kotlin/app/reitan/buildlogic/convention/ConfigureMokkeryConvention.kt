package app.reitan.buildlogic.convention

import dev.mokkery.gradle.MokkeryGradleExtension
import org.gradle.api.Project

internal fun Project.configureMokkery(extension: MokkeryGradleExtension) = extension.apply {
    stubs.allowConcreteClassInstantiation.set(true)
    stubs.allowClassInheritance.set(true)
    ignoreFinalMembers.set(true)
    ignoreInlineMembers.set(true)
}
