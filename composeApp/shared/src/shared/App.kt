package shared

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.KoinApplication

@Composable
fun App() {
	val navController = rememberNavController()

	KoinApplication(application = {
		modules(appModule)
	}) {
		MaterialTheme {
			AppNavGraph(navController)
		}
	}
}