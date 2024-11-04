plugins {
	alias(libs.plugins.sqldelight)
}

kotlin {
	targets.configureEach {
		compilations.configureEach {
			compileTaskProvider.configure {
				compilerOptions {
					freeCompilerArgs.add("-Xexpect-actual-classes")
				}
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