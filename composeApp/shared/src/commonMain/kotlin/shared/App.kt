package shared

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import shared.ui.theme.AppTheme

@Composable
public fun App() {
	val navController = rememberNavController()

	AppTheme {
		AppNavGraph(navController)
	}
}
