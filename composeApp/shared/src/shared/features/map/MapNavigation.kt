package shared.features.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val MAP_ROUTE = "map"

internal fun NavGraphBuilder.mapScreen() {
	composable(MAP_ROUTE) {
		Box(Modifier.fillMaxSize())
	}
}

internal fun NavController.navigateToMapScreen() = navigate(MAP_ROUTE)