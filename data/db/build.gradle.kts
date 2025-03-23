plugins {
	alias(libs.plugins.sqldelight)
}

sqldelight {
	databases {
		create("Database") {
			packageName = "db"
			srcDirs.setFrom("sqldelight")
		}
	}
}
