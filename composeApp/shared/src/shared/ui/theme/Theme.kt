package shared.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.colorResource
import resources.MR

private val colorScheme
	@Composable
	get() = ColorScheme(
		primary = colorResource(if (isSystemInDarkTheme()) MR.colors.primary else MR.colors.primary),
		onPrimary = colorResource(MR.colors.onPrimary),
		primaryContainer = colorResource(MR.colors.primaryContainer),
		onPrimaryContainer = colorResource(MR.colors.onPrimaryContainer),
		inversePrimary = colorResource(MR.colors.inversePrimary),
		secondary = colorResource(MR.colors.secondary),
		onSecondary = colorResource(MR.colors.onSecondary),
		secondaryContainer = colorResource(MR.colors.secondaryContainer),
		onSecondaryContainer = colorResource(MR.colors.onSecondaryContainer),
		tertiary = colorResource(MR.colors.tertiary),
		onTertiary = colorResource(MR.colors.onTertiary),
		tertiaryContainer = colorResource(MR.colors.tertiaryContainer),
		onTertiaryContainer = colorResource(MR.colors.onTertiaryContainer),
		background = colorResource(MR.colors.background),
		onBackground = colorResource(MR.colors.onBackground),
		surface = colorResource(MR.colors.surface),
		onSurface = colorResource(MR.colors.onSurface),
		surfaceVariant = colorResource(MR.colors.surfaceVariant),
		onSurfaceVariant = colorResource(MR.colors.onSurfaceVariant),
		surfaceTint = colorResource(MR.colors.primary),
		inverseSurface = colorResource(MR.colors.inverseSurface),
		inverseOnSurface = colorResource(MR.colors.inverseOnSurface),
		error = colorResource(MR.colors.error),
		onError = colorResource(MR.colors.onError),
		errorContainer = colorResource(MR.colors.errorContainer),
		onErrorContainer = colorResource(MR.colors.onErrorContainer),
		outline = colorResource(MR.colors.outline),
		outlineVariant = colorResource(MR.colors.outlineVariant),
		scrim = colorResource(MR.colors.scrim),
		surfaceBright = colorResource(MR.colors.surfaceBright),
		surfaceContainer = colorResource(MR.colors.surfaceContainer),
		surfaceContainerHigh = colorResource(MR.colors.surfaceContainerHigh),
		surfaceContainerHighest = colorResource(MR.colors.surfaceContainerHighest),
		surfaceContainerLow = colorResource(MR.colors.surfaceContainerLow),
		surfaceContainerLowest = colorResource(MR.colors.surfaceContainerLowest),
		surfaceDim = colorResource(MR.colors.surfaceDim),
	)

@Composable
fun AppTheme(
	content: @Composable () -> Unit,
) {
	MaterialTheme(
		colorScheme = colorScheme,
		typography = AppTypography(),
		content = content,
		shapes = Shapes(extraSmall = RoundedCornerShape(16.dp)),
	)
}

