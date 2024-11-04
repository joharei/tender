package presentation.features.edit.models

import presentation.utils.PlatformLocalDate
import presentation.utils.PlatformLocalTime

data class EditUiState(
	val editMode: EditMode = EditMode.AddNew,
	val name: String? = null,
	val lat: String? = null,
	val latError: Boolean = false,
	val lon: String? = null,
	val lonError: Boolean = false,
	val startDate: PlatformLocalDate? = null,
	val startTime: PlatformLocalTime? = null,
	val saveButtonEnabled: Boolean = false,
	val saveCompleted: Boolean = false,
	val dailyDegreesGoal: Int = 40,
)

enum class EditMode { AddNew, Edit }