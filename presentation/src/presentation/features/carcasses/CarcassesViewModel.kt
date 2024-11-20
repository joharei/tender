package presentation.features.carcasses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.DeleteCarcassUseCase
import domain.GetCarcassesUseCase
import domain.MarkAsDoneUseCase
import domain.models.CarcassWithEstimate
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import presentation.features.carcasses.models.CarcassUiState
import presentation.features.carcasses.models.CarcassesUiState

class CarcassesViewModel(
	getCarcassesUseCase: GetCarcassesUseCase,
	private val deleteCarcassUseCase: DeleteCarcassUseCase,
	private val markAsDoneUseCase: MarkAsDoneUseCase,
) : ViewModel() {
	val uiState: StateFlow<CarcassesUiState> = getCarcassesUseCase()
		.map { carcassesWithEstimates ->
			val grouped = carcassesWithEstimates.groupBy { it.status is CarcassWithEstimate.Status.Done }
			CarcassesUiState(
				activeCarcasses = grouped[false]?.map(::toUiState).orEmpty(),
				doneCarcasses = grouped[true]?.map(::toUiState).orEmpty(),
				loading = false,
			)
		}
		.stateIn(
			viewModelScope,
			SharingStarted.WhileSubscribed(),
			CarcassesUiState(activeCarcasses = emptyList(), doneCarcasses = emptyList(), loading = true),
		)

	private fun toUiState(carcassWithEstimate: CarcassWithEstimate) = with(carcassWithEstimate) {
		CarcassUiState(
			id = id,
			name = name,
			durationSinceStarted = durationSinceStarted,
			status = when (val status = status) {
				is CarcassWithEstimate.Status.Done -> CarcassUiState.Status.Done(doneDailyDegrees = status.doneDailyDegrees)
				is CarcassWithEstimate.Status.InProgress -> CarcassUiState.Status.InProgress(
					durationUntilDueEstimate = status.durationUntilDueEstimate,
					progress = status.progress,
					currentDailyDegrees = status.currentDailyDegrees,
				)
			},
		)
	}

	fun deleteCarcass(carcassId: Long) {
		viewModelScope.launch {
			deleteCarcassUseCase(carcassId)
		}
	}

	fun markAsDone(carcassId: Long, doneDate: Instant, currentDailyDegrees: Double) {
		viewModelScope.launch {
			markAsDoneUseCase(carcassId = carcassId, doneDate = doneDate, currentDailyDegrees = currentDailyDegrees)
		}
	}

}
