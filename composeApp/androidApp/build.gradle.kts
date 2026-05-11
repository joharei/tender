plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.compose.multiplatform)
	alias(libs.plugins.compose.compiler)
	alias(libs.plugins.app.versioning)
}

appVersioning {
	overrideVersionName { gitTag, _, variantInfo ->
		val version = gitTag.rawTagName.drop(1)
		if (gitTag.commitsSinceLatestTag == 0) {
			version
		} else {
			"$version-${gitTag.commitsSinceLatestTag}-${gitTag.commitHash}"
		}.let {
			if (variantInfo.isDebugBuild) "$it-DEBUG" else it
		}
	}
	overrideVersionCode { gitTag, _, _ ->
		val majorMultiplier = 10000000
		val version = gitTag.rawTagName.drop(1).toInt()
		version * majorMultiplier + gitTag.commitsSinceLatestTag
	}
}

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
