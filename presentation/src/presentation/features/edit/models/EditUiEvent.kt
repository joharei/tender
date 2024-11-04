package presentation.features.edit.models

import presentation.utils.PlatformLocalDate
import presentation.utils.PlatformLocalTime

sealed class EditUiEvent {
	data class OnSetName(val name: String) : EditUiEvent()
	data class OnSetLat(val lat: String) : EditUiEvent()
	data class OnSetLon(val lon: String) : EditUiEvent()
	data class OnSetStartDate(val startDate: PlatformLocalDate) : EditUiEvent()
	data class OnSetStartTime(val startTime: PlatformLocalTime) : EditUiEvent()
	data class OnSetDailyDegreesGoal(val dailyDegreesGoal: Int) : EditUiEvent()
	data object OnSave : EditUiEvent()
}