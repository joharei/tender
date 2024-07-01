import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import shared.App

fun main() = application {
	Window(title = "Tender", onCloseRequest = ::exitApplication) {
		App()
	}
}