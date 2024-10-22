package presentation.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

actual typealias PlatformInstant = Instant

internal actual fun PlatformInstant.fromPlatformLocalDate(): Instant = this

internal actual fun Instant.toPlatform(): PlatformInstant = this


actual typealias PlatformLocalDate = LocalDate

internal actual fun PlatformLocalDate.fromPlatformLocalDate(): LocalDate = this

internal actual fun LocalDate.toPlatform(): PlatformLocalDate = this


actual typealias PlatformLocalTime = LocalTime

internal actual fun PlatformLocalTime.fromPlatformLocalTime(): LocalTime = this

internal actual fun LocalTime.toPlatform(): PlatformLocalTime = this