package presentation.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

expect class PlatformInstant

internal expect fun PlatformInstant.fromPlatformLocalDate(): Instant

internal expect fun Instant.toPlatform(): PlatformInstant

expect class PlatformLocalDate

internal expect fun PlatformLocalDate.fromPlatformLocalDate(): LocalDate

internal expect fun LocalDate.toPlatform(): PlatformLocalDate

expect class PlatformLocalTime

internal expect fun PlatformLocalTime.fromPlatformLocalTime(): LocalTime

internal expect fun LocalTime.toPlatform(): PlatformLocalTime