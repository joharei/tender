import SwiftUI
import kotlin

@main
struct iOSApp: App {
	init() {
		KoinKt.doInitKoin()
	}

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
