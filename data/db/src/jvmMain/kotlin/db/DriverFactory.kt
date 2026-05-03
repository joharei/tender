package db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

internal actual class DriverFactory {
	actual fun createDriver(): SqlDriver {
		val driver = JdbcSqliteDriver(url = "jdbc:sqlite:database.db", schema = Database.Schema)
		Database.Schema.create(driver)
		return driver
	}
}