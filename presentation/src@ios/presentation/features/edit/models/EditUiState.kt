package presentation.features.edit.models

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.toNSDateComponents
import platform.Foundation.NSCalendar
import platform.Foundation.NSDate
import presentation.utils.fromPlatformLocalDate
import presentation.utils.fromPlatformLocalTime

fun initialEditUiState(): EditUiState = EditUiState()

val EditUiState.startDateTime: NSDate
	get() {
		val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
		val combined = LocalDateTime(
			date = startDate?.fromPlatformLocalDate() ?: now.date,
			time = startTime?.fromPlatformLocalTime() ?: now.time,
		).toNSDateComponents()
		return NSCalendar.currentCalendar.dateFromComponents(combined) ?: NSDate()
	}
