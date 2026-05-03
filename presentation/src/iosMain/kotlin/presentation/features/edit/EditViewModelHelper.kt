package presentation.features.edit

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import platform.Foundation.NSNumber

public class EditViewModelHelper : KoinComponent {
	public fun viewModel(carcassId: NSNumber?): EditViewModel = get { parametersOf(carcassId) }
}
