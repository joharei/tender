package shared.features.addNew

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.AddCarcassUseCase
import domain.models.LatLon
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import shared.features.addNew.models.AddNewUiEvent
import shared.features.addNew.models.AddNewUiState
import shared.utils.combine

class AddNewViewModel(
	private val addCarcassUseCase: AddCarcassUseCase,
) : ViewModel() {
	private val name = MutableStateFlow<String?>(null)
	private val lat = MutableStateFlow<String?>("62.3964924")
	private val lon = MutableStateFlow<String?>("6.5987279")
	private val startDate = MutableStateFlow<LocalDate?>(null)
	private val startTime = MutableStateFlow<LocalTime?>(null)
	private val saveCompleted = MutableStateFlow(false)
	private val latError = lat.map { it != null && it.toDoubleOrNull() == null }
		.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)
	private val lonError = lon.map { it != null && it.toDoubleOrNull() == null }
		.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

	val uiState: StateFlow<AddNewUiState> =
		combine(
			name,
			lat,
			latError,
			lon,
			lonError,
			startDate,
			startTime,
			saveCompleted,
		) { name, lat, latError, lon, lonError, startDate, startTime, saveCompleted ->
			AddNewUiState(
				name = name,
				lat = lat,
				latError = latError,
				lon = lon,
				lonError = lonError,
				startDate = startDate,
				startTime = startTime,
				saveButtonEnabled = name != null && lat != null && !latError && lon != null && !lonError && startDate != null && startTime != null,
				saveCompleted = saveCompleted,
			)
		}
			.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AddNewUiState())

	fun onUiEvent(event: AddNewUiEvent) {
		when (event) {
			is AddNewUiEvent.OnSetLat -> lat.value = event.lat
			is AddNewUiEvent.OnSetLon -> lon.value = event.lon
			is AddNewUiEvent.OnSetName -> name.value = event.name
			is AddNewUiEvent.OnSetStartDate -> startDate.value = event.startDate
			is AddNewUiEvent.OnSetStartTime -> startTime.value = event.startTime
			AddNewUiEvent.OnSave -> save()
		}
	}

	private fun save() {
		if (latError.value || lonError.value) {
			return
		}
		val name = this.name.value ?: return
		val lat = this.lat.value?.toDoubleOrNull() ?: return
		val lon = this.lon.value?.toDoubleOrNull() ?: return
		val startDate = this.startDate.value ?: return
		val startTime = this.startTime.value ?: return
		viewModelScope.launch {
			addCarcassUseCase(
				name = name,
				startDate = startDate.atTime(startTime).toInstant(TimeZone.currentSystemDefault()),
				location = LatLon(lat = lat, lon = lon),
			)
		}
		saveCompleted.value = true
	}
}