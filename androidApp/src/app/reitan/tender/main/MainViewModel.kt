package app.reitan.tender.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.reitan.tender.main.models.InputFields
import app.reitan.tender.main.models.MainUiState
import domain.Calculate24HoursDegreesUseCase
import kotlinx.coroutines.flow.*
import kotlinx.datetime.LocalDate
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
	private val calculate24HoursDegreesUseCase: Calculate24HoursDegreesUseCase,
) : ViewModel() {
	private val lat = MutableStateFlow("")
	private val lon = MutableStateFlow("")
	private val startDate = MutableStateFlow<LocalDate?>(null)
	private val endDate = MutableStateFlow<LocalDate?>(null)

	private val inputFields = MutableStateFlow<InputFields?>(null)
	private val calculated24HoursDegrees =
		inputFields
			.map {
				it?.let {
					calculate24HoursDegreesUseCase(lat = it.lat, lon = it.lon, startDate = it.startDate, endDate = it.endDate)
				}
			}

	val uiState: StateFlow<MainUiState> =
		combine(
			lat,
			lon,
			startDate,
			endDate,
			calculated24HoursDegrees,
		) { lat, lon, startDate, endDate, calculated24HoursDegrees ->
			MainUiState(
				lat = lat,
				lon = lon,
				startDate = startDate,
				endDate = endDate,
				calculated24HoursDegrees = calculated24HoursDegrees,
			)
		}
			.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MainUiState())

	fun setLat(lat: String) {
		this.lat.value = lat
	}

	fun setLon(lon: String) {
		this.lon.value = lon
	}

	fun setStartDate(startDate: LocalDate) {
		this.startDate.value = startDate
	}

	fun setEndDate(endDate: LocalDate) {
		this.endDate.value = endDate
	}
	
	fun calculate() {
		val lat = this.lat.value.toDoubleOrNull()
		val lon = this.lon.value.toDoubleOrNull()
		val startDate = this.startDate.value
		val endDate = this.endDate.value
		if (lat != null && lon != null && startDate != null && endDate != null) {
			inputFields.value = InputFields(lat = lat, lon = lon, startDate = startDate, endDate = endDate)
		}
	}
}