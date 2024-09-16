package shared

import db.dbModule
import domain.domainModule
import network.networkModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import shared.features.edit.EditViewModel
import shared.features.carcasses.CarcassesViewModel

internal val appModule = module {
	includes(domainModule, dbModule, networkModule)

	viewModelOf(::CarcassesViewModel)
	viewModelOf(::EditViewModel)
}