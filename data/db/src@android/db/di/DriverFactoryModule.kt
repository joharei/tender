package db.di

import db.DriverFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val driverFactoryModule = module {
	singleOf(::DriverFactory)
}