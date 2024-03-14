package app.reitan.tender

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import app.reitan.tender.main.MainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.compose.KoinApplication
import org.koin.ksp.generated.module

@Composable
fun App() {
	val context = LocalContext.current
	KoinApplication(application = {
		androidLogger()
		androidContext(context.applicationContext)
		modules(AppModule().module)
	}) {
		MaterialTheme {
			MainScreen()
		}
	}
}