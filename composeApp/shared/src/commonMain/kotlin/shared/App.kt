package shared

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.KoinContext
import shared.ui.theme.AppTheme

@Composable
fun App() {
	val navController = rememberNavController()

	KoinContext {
		AppTheme {
			AppNavGraph(navController)
		}
	}
}