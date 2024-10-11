package presentation.features.edit

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EditViewModelHelper : KoinComponent {
	val viewModel: EditViewModel by inject()
}