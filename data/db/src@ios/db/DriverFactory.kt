package db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

internal actual class DriverFactory {
	actual fun createDriver(): SqlDriver {
		return NativeSqliteDriver(Database.Schema, "database.db")
	}
}