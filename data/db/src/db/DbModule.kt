package db

import db.di.driverFactoryModule
import domain.repositories.CarcassRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dbModule = module {
	includes(driverFactoryModule)
	single { createDatabase(get()) }
	single { get<Database>().carcassQueries }

	factoryOf(::CarcassRepositoryImpl) bind CarcassRepository::class
}