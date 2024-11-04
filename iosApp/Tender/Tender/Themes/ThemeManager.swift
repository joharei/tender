import SwiftUI
/**
 Theme Manager
 */
class ThemeManager: ObservableObject {
	@Published var selectedTheme: ThemeProtocol = Main()
	
	func setTheme(_ theme: ThemeProtocol) {
		selectedTheme = theme
	}
}
