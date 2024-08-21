package shared.features.addNew.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

sealed interface AddNewUiEvent {
	data class OnSetName(val name: String) : AddNewUiEvent
	data class OnSetLat(val lat: String) : AddNewUiEvent
	data class OnSetLon(val lon: String) : AddNewUiEvent
	data class OnSetStartDate(val startDate: LocalDate) : AddNewUiEvent
	data class OnSetStartTime(val startTime: LocalTime) : AddNewUiEvent
	data object OnSave : AddNewUiEvent
}