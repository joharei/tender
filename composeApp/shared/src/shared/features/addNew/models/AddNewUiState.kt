package shared.features.addNew.models

import domain.models.LatLon
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class AddNewUiState(
	val name: String? = null,
	val lat: String? = null,
	val latError: Boolean = false,
	val lon: String? = null,
	val lonError: Boolean = false,
	val startDate: LocalDate? = null,
	val startTime: LocalTime? = null,
	val saveButtonEnabled: Boolean = false,
	val saveCompleted: Boolean = false,
)