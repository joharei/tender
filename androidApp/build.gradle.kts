android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        versionCode = 1
        versionName = "1.0"
    }

    dependencies {
        implementation(platform(libs.androidx.compose.bom))
    }
}

