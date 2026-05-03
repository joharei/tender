package presentation.features.edit.models

import presentation.utils.PlatformLocalDate
import presentation.utils.PlatformLocalTime

public sealed class EditUiEvent {
	public data class OnSetName(val name: String) : EditUiEvent()
	public data class OnSetLat(val lat: String) : EditUiEvent()
	public data class OnSetLon(val lon: String) : EditUiEvent()
	public data class OnSetStartDate(val startDate: PlatformLocalDate) : EditUiEvent()
	public data class OnSetStartTime(val startTime: PlatformLocalTime) : EditUiEvent()
	public data class OnSetDailyDegreesGoal(val dailyDegreesGoal: Int) : EditUiEvent()
	public data object OnSave : EditUiEvent()
}
