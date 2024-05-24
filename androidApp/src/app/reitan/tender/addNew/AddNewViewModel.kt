package app.reitan.tender.addNew

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.reitan.tender.addNew.models.InputFields
import app.reitan.tender.addNew.models.LatLon
import app.reitan.tender.addNew.models.MainUiState
import domain.Calculate24HoursDegreesUseCase
import kotlinx.coroutines.flow.*
import kotlinx.datetime.LocalDate
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AddNewViewModel(
	private val calculate24HoursDegreesUseCase: Calculate24HoursDegreesUseCase,
) : ViewModel() {
	private val location = MutableStateFlow<LatLon?>(LatLon(62.3964924, 6.5987279))
	private val startDate = MutableStateFlow<LocalDate?>(null)

	private val inputFields = MutableStateFlow<InputFields?>(null)
	private val calculated24HoursDegrees =
		inputFields
			.map {
				it?.let {
					calculate24HoursDegreesUseCase(lat = it.location.lat, lon = it.location.lon, startDate = it.startDate)
				}
			}

	val uiState: StateFlow<MainUiState> =
		combine(
			location,
			startDate,
			calculated24HoursDegrees,
		) { location, startDate, calculated24HoursDegrees ->
			MainUiState(
				location = location,
				startDate = startDate,
				calculated24HoursDegrees = calculated24HoursDegrees,
			)
		}
			.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MainUiState())

	fun setLocation(location: LatLon) {
		this.location.value = location
	}

	fun setStartDate(startDate: LocalDate) {
		this.startDate.value = startDate
	}
	
	fun calculate() {
		val location = this.location.value
		val startDate = this.startDate.value
		if (location != null && startDate != null) {
			inputFields.value = InputFields(location = location, startDate = startDate)
		}
	}
}