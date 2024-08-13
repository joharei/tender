import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import shared.App

fun main() = singleWindowApplication(
	title = "Tender",
	state = WindowState(width = 1000.dp, height = 800.dp),
) {
	App()
}