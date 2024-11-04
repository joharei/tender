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