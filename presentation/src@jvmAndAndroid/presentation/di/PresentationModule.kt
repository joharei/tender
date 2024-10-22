package presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import presentation.features.carcasses.CarcassesViewModel
import presentation.features.edit.EditViewModel

actual val presentationModule = module {
	viewModelOf(::CarcassesViewModel)
	viewModelOf(::EditViewModel)
}