package db

import app.cash.sqldelight.adapter.primitive.IntColumnAdapter
import app.cash.sqldelight.db.SqlDriver

internal expect class DriverFactory {
	fun createDriver(): SqlDriver
}

internal fun createDatabase(driverFactory: DriverFactory): Database {
	val driver = driverFactory.createDriver()
	val database = Database(
		driver = driver,
		carcassAdapter = Carcass.Adapter(
			daily_degrees_goalAdapter = IntColumnAdapter,
			done_daily_degreesAdapter = IntColumnAdapter,
		),
	)

	return database
}