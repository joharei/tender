package presentation.utils

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toKotlinInstant
import kotlinx.datetime.toNSDate
import kotlinx.datetime.toNSDateComponents
import platform.Foundation.NSDate
import platform.Foundation.NSDateComponents

//import platform.Foundation.*

actual typealias PlatformInstant = NSDate

internal actual fun PlatformInstant.fromPlatformLocalDate(): Instant = toKotlinInstant()

internal actual fun Instant.toPlatform(): PlatformInstant = toNSDate()


actual typealias PlatformLocalDate = NSDateComponents

internal actual fun PlatformLocalDate.fromPlatformLocalDate(): LocalDate {
	return LocalDate(
		year = year.toInt(),
		monthNumber = month.toInt(),
		dayOfMonth = day.toInt(),
	)
}

internal actual fun LocalDate.toPlatform(): PlatformLocalDate = toNSDateComponents()


actual typealias PlatformLocalTime = NSDateComponents

internal actual fun PlatformLocalTime.fromPlatformLocalTime(): LocalTime {
	return LocalTime(
		hour = hour.toInt(),
		minute = minute.toInt(),
		second = second.toInt(),
		nanosecond = nanosecond.toInt(),
	)
}

@OptIn(ExperimentalForeignApi::class)
internal actual fun LocalTime.toPlatform(): PlatformLocalTime {
	val components = NSDateComponents()
	components.hour = hour.convert()
	components.minute = minute.convert()
	components.second = second.convert()
	components.nanosecond = nanosecond.convert()
	return components
}