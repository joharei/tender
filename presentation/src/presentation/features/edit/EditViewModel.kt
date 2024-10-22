package presentation.features.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import domain.AddCarcassUseCase
import domain.GetCarcassUseCase
import domain.UpdateCarcassUseCase
import domain.models.Carcass
import domain.models.LatLon
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import presentation.features.edit.models.EditMode
import presentation.features.edit.models.EditUiEvent
import presentation.features.edit.models.EditUiState
import presentation.utils.combine
import presentation.utils.fromPlatformLocalDate
import presentation.utils.fromPlatformLocalTime
import presentation.utils.toPlatform

class EditViewModel(
	savedStateHandle: SavedStateHandle,
	getCarcassUseCase: GetCarcassUseCase,
	private val addCarcassUseCase: AddCarcassUseCase,
	private val updateCarcassUseCase: UpdateCarcassUseCase,
) : ViewModel() {
	private val args = savedStateHandle.toRoute<EditDestination>()

	init {
		if (args.carcassId != null) {
			viewModelScope.launch {
				getCarcassUseCase(args.carcassId)
					.filterNotNull()
					.collect {
						name.value = it.name
						lat.value = it.location.lat.toString()
						lon.value = it.location.lon.toString()
						startDate.value = it.startDate.toLocalDateTime(TimeZone.currentSystemDefault()).date
						startTime.value = it.startDate.toLocalDateTime(TimeZone.currentSystemDefault()).time
					}
			}
		}
	}

	private val name = MutableStateFlow<String?>(null)
	private val lat = MutableStateFlow<String?>("62.3964924")
	private val lon = MutableStateFlow<String?>("6.5987279")
	private val startDate = MutableStateFlow<LocalDate?>(null)
	private val startTime = MutableStateFlow<LocalTime?>(null)
	private val dailyDegreesGoal = MutableStateFlow(40)
	private val saveCompleted = MutableStateFlow(false)
	private val latError = lat.map { it != null && it.toDoubleOrNull() == null }
		.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)
	private val lonError = lon.map { it != null && it.toDoubleOrNull() == null }
		.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

	val uiState: StateFlow<EditUiState> =
		combine(
			name,
			lat,
			latError,
			lon,
			lonError,
			startDate,
			startTime,
			dailyDegreesGoal,
			saveCompleted,
		) { name, lat, latError, lon, lonError, startDate, startTime, dailyDegreesGoal, saveCompleted ->
			EditUiState(
				editMode = if (args.carcassId != null) EditMode.Edit else EditMode.AddNew,
				name = name,
				lat = lat,
				latError = latError,
				lon = lon,
				lonError = lonError,
				startDate = startDate?.toPlatform(),
				startTime = startTime?.toPlatform(),
				dailyDegreesGoal = dailyDegreesGoal,
				saveButtonEnabled = name != null && lat != null && !latError && lon != null && !lonError && startDate != null && startTime != null,
				saveCompleted = saveCompleted,
			)
		}
			.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), EditUiState(dailyDegreesGoal = dailyDegreesGoal.value))

	fun onUiEvent(event: EditUiEvent) {
		Napier.d("onUiEvent: $event")
		when (event) {
			is EditUiEvent.OnSetLat -> lat.value = event.lat
			is EditUiEvent.OnSetLon -> lon.value = event.lon
			is EditUiEvent.OnSetName -> name.value = event.name
			is EditUiEvent.OnSetStartDate -> startDate.value = event.startDate.fromPlatformLocalDate()
			is EditUiEvent.OnSetStartTime -> startTime.value = event.startTime.fromPlatformLocalTime()
			is EditUiEvent.OnSetDailyDegreesGoal -> dailyDegreesGoal.value = event.dailyDegreesGoal
			EditUiEvent.OnSave -> save()
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
		val dailyDegreesGoal = this.dailyDegreesGoal.value

		val startInstant = startDate.atTime(startTime).toInstant(TimeZone.currentSystemDefault())
		val location = LatLon(lat = lat, lon = lon)
		viewModelScope.launch {
			if (args.carcassId != null) {
				updateCarcassUseCase(
					Carcass(
						id = args.carcassId,
						name = name,
						startDate = startInstant,
						location = location,
						dailyDegreesGoal = dailyDegreesGoal,
					),
				)
			} else {
				addCarcassUseCase(
					name = name,
					startDate = startInstant,
					location = location,
					dailyDegreesGoal = dailyDegreesGoal,
				)
			}
		}
		saveCompleted.value = true
	}
}
