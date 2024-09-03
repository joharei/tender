package shared.features.carcasses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.GetCarcassesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.Clock
import shared.features.carcasses.models.CarcassUiState
import shared.features.carcasses.models.CarcassesUiState

class CarcassesViewModel(
	getCarcassesUseCase: GetCarcassesUseCase,
) : ViewModel() {
	val uiState: StateFlow<CarcassesUiState> = getCarcassesUseCase()
		.map { carcassesWithEstimates ->
			CarcassesUiState(
				carcasses = carcassesWithEstimates.map {
					CarcassUiState(
						id = it.id,
						name = it.name,
						durationSinceStarted = Clock.System.now() - it.started,
						durationUntilDueEstimate = it.dueEstimate - Clock.System.now(),
						progress = it.progress,
						current24HoursDegrees = it.current24HoursDegrees,
					)
				},
				loading = false,
			)
		}
		.stateIn(
			viewModelScope,
			SharingStarted.WhileSubscribed(),
			CarcassesUiState(carcasses = emptyList(), loading = true),
		)
}