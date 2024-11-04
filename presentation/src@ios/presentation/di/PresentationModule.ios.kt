package presentation.di

import androidx.lifecycle.SavedStateHandle
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import presentation.features.carcasses.CarcassesViewModel
import presentation.features.edit.EditViewModel

actual val presentationModule = module {
	viewModelOf(::CarcassesViewModel)
	viewModel { (carcassId: Long?) ->
		EditViewModel(savedStateHandle = SavedStateHandle(mapOf("carcassId" to carcassId)), get(), get(), get())
	}
}