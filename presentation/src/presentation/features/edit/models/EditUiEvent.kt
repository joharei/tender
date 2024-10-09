package presentation.features.edit.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

sealed interface EditUiEvent {
	data class OnSetName(val name: String) : EditUiEvent
	data class OnSetLat(val lat: String) : EditUiEvent
	data class OnSetLon(val lon: String) : EditUiEvent
	data class OnSetStartDate(val startDate: LocalDate) : EditUiEvent
	data class OnSetStartTime(val startTime: LocalTime) : EditUiEvent
	data class OnSetDailyDegreesGoal(val dailyDegreesGoal: Int) : EditUiEvent
	data object OnSave : EditUiEvent
}