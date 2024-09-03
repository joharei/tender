package shared.utils

actual fun Double.format(): String {
	return java.text.DecimalFormat().apply {
		maximumFractionDigits = 1
		isDecimalSeparatorAlwaysShown = false
	}.format(this)
}