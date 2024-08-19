plugins {
	alias(libs.plugins.ksp)
}

dependencies {
	configurations
		.filter { it.name.startsWith("ksp") && it.name.contains("Test") }
		.forEach {
			add(it.name, libs.mockative.processor)
		}
}