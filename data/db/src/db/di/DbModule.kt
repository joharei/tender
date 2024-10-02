package db.di

import db.CarcassRepositoryImpl
import db.Database
import db.createDatabase
import db.mappers.CarcassMapper
import domain.repositories.CarcassRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dbModule = module {
	includes(driverFactoryModule)
	single { createDatabase(get()) }
	single { get<Database>().carcassQueries }

	factoryOf(::CarcassRepositoryImpl) bind CarcassRepository::class
	factoryOf(::CarcassMapper)
}