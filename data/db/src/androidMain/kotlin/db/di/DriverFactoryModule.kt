package db.di

import db.DriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

public actual val driverFactoryModule: Module = module {
	singleOf(::DriverFactory)
}
