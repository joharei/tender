import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format
import resources.MR
import kotlin.time.Duration

public fun getCarcassLabelDailyDegrees(dailyDegrees: String, percent: Int): StringDesc {
	return MR.strings.carcass_label_daily_degrees.format(dailyDegrees, percent)
}

public fun getCarcassLabelDone(doneDailyDegrees: Int): StringDesc {
	return MR.strings.carcass_label_done.format(doneDailyDegrees)
}

private fun Duration.format(): StringDesc {
	toComponents { days, hours, _, _, _ ->
		return MR.strings.carcass_duration_short_format.format(days, hours)
	}
}

public fun getCarcassDurationAgo(durationSinceStarted: Duration): StringDesc {
	return MR.strings.carcass_duration_ago_format.format(durationSinceStarted.format())
}

public fun getCarcassDurationIn(durationUntilDueEstimate: Duration): StringDesc {
	return MR.strings.carcass_duration_in_format.format(durationUntilDueEstimate.format())
}

public fun getCarcassLabelConfirmDelete(name: String): StringDesc {
	return MR.strings.carcass_label_confirm_delete.format(name)
}
