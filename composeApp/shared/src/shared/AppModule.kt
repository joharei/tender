package shared

import db.dbModule
import domain.domainModule
import network.networkModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import shared.features.addNew.AddNewViewModel

internal val appModule = module {
	includes(domainModule, dbModule, networkModule)

	viewModelOf(::AddNewViewModel)
}