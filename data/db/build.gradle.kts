plugins {
	alias(libs.plugins.sqldelight)
}

kotlin {
	targets.configureEach {
		compilations.configureEach {
			compilerOptions.configure {
				freeCompilerArgs.add("-Xexpect-actual-classes")
			}
		}
	}
}

sqldelight {
	databases {
		create("Database") {
			packageName = "db"
			srcDirs.setFrom("sqldelight")
		}
	}
}