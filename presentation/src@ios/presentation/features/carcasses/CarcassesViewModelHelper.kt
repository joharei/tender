package presentation.features.carcasses

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CarcassesViewModelHelper : KoinComponent {
	val viewModel: CarcassesViewModel by inject()
}
