import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format
import resources.MR
import kotlin.time.Duration

fun getCarcassLabelDailyDegrees(dailyDegrees: String, percent: Int): StringDesc {
	return MR.strings.carcass_label_daily_degrees.format(dailyDegrees, percent)
}

private fun Duration.format(): StringDesc {
	toComponents { days, hours, _, _, _ ->
		return MR.strings.carcass_duration_short_format.format(days, hours)
	}
}

fun getCarcassDurationAgo(durationSinceStarted: Duration): StringDesc {
	return MR.strings.carcass_duration_ago_format.format(durationSinceStarted.format())
}

fun getCarcassDurationIn(durationUntilDueEstimate: Duration): StringDesc {
	return MR.strings.carcass_duration_in_format.format(durationUntilDueEstimate.format())
}

fun getCarcassLabelConfirmDelete(name: String): StringDesc {
	return MR.strings.carcass_label_confirm_delete.format(name)
}