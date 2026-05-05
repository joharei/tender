package presentation.features.carcasses

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

public class CarcassesViewModelHelper : KoinComponent {
	public val viewModel: CarcassesViewModel by inject()
}
