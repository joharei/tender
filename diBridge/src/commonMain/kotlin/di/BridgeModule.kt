package di

import db.di.dbModule
import domain.domainModule
import network.networkModule
import org.koin.core.module.Module
import org.koin.dsl.module

public val bridgeModule: Module = module {
	includes(domainModule, dbModule, networkModule)
}
