package presentation.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import presentation.features.carcasses.CarcassesViewModel
import presentation.features.edit.EditViewModel

public actual val presentationModule: Module = module {
	viewModelOf(::CarcassesViewModel)
	viewModelOf(::EditViewModel)
}
