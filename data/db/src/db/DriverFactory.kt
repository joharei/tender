package db

import app.cash.sqldelight.db.SqlDriver

internal expect class DriverFactory {
	fun createDriver(): SqlDriver
}

internal fun createDatabase(driverFactory: DriverFactory): Database {
	val driver = driverFactory.createDriver()
	val database = Database(driver)
	
	return database
}