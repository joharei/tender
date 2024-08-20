package shared.features.carcasses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.AddCarcassUseCase
import domain.GetCarcassesUseCase
import kotlinx.coroutines.flow.*
import shared.features.carcasses.models.CarcassesUiState

class CarcassesViewModel(
	private val getCarcassesUseCase: GetCarcassesUseCase,
	private val addCarcassUseCase: AddCarcassUseCase,
) : ViewModel() {
	val uiState: StateFlow<CarcassesUiState> = getCarcassesUseCase()
		.map {
			CarcassesUiState(
				carcasses = it,
				loading = false,
			)
		}
		.stateIn(
			viewModelScope,
			SharingStarted.WhileSubscribed(),
			CarcassesUiState(carcasses = emptyList(), loading = true),
		)
}