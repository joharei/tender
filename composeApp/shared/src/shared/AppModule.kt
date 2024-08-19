package shared

import domain.domainModule
import network.networkModule
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module
import shared.features.addNew.AddNewViewModel

internal val appModule = module {
	includes(domainModule, networkModule)

	viewModel { AddNewViewModel(get()) }
}