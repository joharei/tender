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
}
