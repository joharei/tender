package di

import db.di.dbModule
import domain.domainModule
import network.networkModule
import org.koin.dsl.module

val bridgeModule = module {
	includes(domainModule, dbModule, networkModule)
}