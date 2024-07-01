package shared

import shared.features.addNew.AddNewViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.ksp.generated.module

fun sharedModule() = module {
	includes(AppModule().module)
	
	viewModelOf(::AddNewViewModel)
}