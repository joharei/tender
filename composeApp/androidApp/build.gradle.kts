plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.compose.multiplatform)
	alias(libs.plugins.compose.compiler)
}

// TODO: set up signing

android {
	namespace = "app.reitan.tender"
	compileSdk = libs.versions.android.compileSdk.get().toInt()

	defaultConfig {
		applicationId = "app.reitan.tender"
		minSdk = libs.versions.android.minSdk.get().toInt()
		targetSdk = libs.versions.android.targetSdk.get().toInt()
		versionCode = 1
		versionName = "1.0"
	}

	compileOptions {
		JavaVersion.toVersion(libs.versions.jvmTarget.get().toInt()).let {
			sourceCompatibility = it
			targetCompatibility = it
		}
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}

	signingConfigs {
		create("sharedDebug") {
			storeFile = project.file("debug.jks")
			storePassword = "android"
			keyAlias = "androiddebugkey"
			keyPassword = "android"
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = true
			isShrinkResources = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro",
			)

			if (System.getenv("CI") == null) {
				signingConfig = getByName("debug").signingConfig
			}
		}

		debug {
			applicationIdSuffix = ".debug"
			versionNameSuffix = "-DEBUG"
			isDebuggable = true
			signingConfig = signingConfigs.getByName("sharedDebug")
		}
	}

	dependencies {
		implementation(projects.composeApp.shared)

		implementation(libs.compose.foundation)
		implementation(libs.compose.runtime)
		implementation(libs.compose.ui)
		implementation(libs.androidx.activity.compose)
		implementation(libs.koin.android)
	}
}
