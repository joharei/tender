package shared.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.compose.fontFamilyResource
import resources.MR

@Composable
fun DisplayFontFamily() = fontFamilyResource(MR.fonts.gilda_display_regular)

// Default Material 3 typography values
val baseline = Typography()

@Composable
fun AppTypography(): Typography {
	val displayFontFamily = DisplayFontFamily()
	return Typography(
		displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
		displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
		displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
		headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
		headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
		headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
		titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
		titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
		titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
		bodyLarge = baseline.bodyLarge/*.copy(fontFamily = bodyFontFamily)*/,
		bodyMedium = baseline.bodyMedium/*.copy(fontFamily = bodyFontFamily)*/,
		bodySmall = baseline.bodySmall/*.copy(fontFamily = bodyFontFamily)*/,
		labelLarge = baseline.labelLarge/*.copy(fontFamily = bodyFontFamily)*/,
		labelMedium = baseline.labelMedium/*.copy(fontFamily = bodyFontFamily)*/,
		labelSmall = baseline.labelSmall/*.copy(fontFamily = bodyFontFamily)*/,
	)
}

