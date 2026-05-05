package app.reitan.buildlogic.convention

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.NamedDomainObjectCollection
import org.jetbrains.kotlin.gradle.plugin.KotlinTarget

internal fun NamedDomainObjectCollection<KotlinTarget>.android(): KotlinMultiplatformAndroidLibraryTarget? =
    this.findByName("android") as? KotlinMultiplatformAndroidLibraryTarget
