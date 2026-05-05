package presentation.utils

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle

public actual fun Double.format(): String {
	return NSNumberFormatter().apply {
		numberStyle = NSNumberFormatterDecimalStyle
		maximumFractionDigits = 1u
	}.stringFromNumber(NSNumber(this)).orEmpty()
}
