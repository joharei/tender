package presentation.features.edit

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import platform.Foundation.NSNumber

class EditViewModelHelper : KoinComponent {
	fun viewModel(carcassId: NSNumber?): EditViewModel = get { parametersOf(carcassId as Long?) }
}