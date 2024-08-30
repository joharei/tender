package shared

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.KoinContext

@Composable
fun App() {
	val navController = rememberNavController()

	KoinContext {
		MaterialTheme {
			AppNavGraph(navController)
		}
	}
}