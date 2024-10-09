package presentation.features.edit.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class EditUiState(
	val editMode: EditMode = EditMode.AddNew,
	val name: String? = null,
	val lat: String? = null,
	val latError: Boolean = false,
	val lon: String? = null,
	val lonError: Boolean = false,
	val startDate: LocalDate? = null,
	val startTime: LocalTime? = null,
	val saveButtonEnabled: Boolean = false,
	val saveCompleted: Boolean = false,
	val dailyDegreesGoal: Int,
)

enum class EditMode { AddNew, Edit }