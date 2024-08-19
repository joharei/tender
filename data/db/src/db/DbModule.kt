package db

import db.di.driverFactoryModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dbModule = module {
	includes(driverFactoryModule)

	single { createDatabase(get()) }
	
	singleOf(::CarcassRepositoryImpl)
}