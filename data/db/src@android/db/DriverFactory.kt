package db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

internal actual class DriverFactory(private val context: Context) {
	actual fun createDriver(): SqlDriver {
		return AndroidSqliteDriver(Database.Schema, context, "database.db")
	}
}