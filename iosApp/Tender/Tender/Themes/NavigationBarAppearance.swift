//
//  NavigationViewAppearance.swift
//  Tender
//
//  Created by Johan Reitan on 23/10/2024.
//

import SwiftUI
import Shared

func setupNavigationBarAppearance(themeManager: ThemeManager) {
	// this is not the same as manipulating the proxy directly
	let appearance = UINavigationBarAppearance()

	// this overrides everything you have set up earlier.
	appearance.configureWithTransparentBackground()

	// this only applies to big titles
	appearance.largeTitleTextAttributes = [
		.font : MR.fonts().gilda_display_regular.uiFont(withSize: 32),
		NSAttributedString.Key.foregroundColor : UIColor(themeManager.selectedTheme.onBackground)
	]
	// this only applies to small titles
	appearance.titleTextAttributes = [
		.font : MR.fonts().gilda_display_regular.uiFont(withSize: 22),
		NSAttributedString.Key.foregroundColor : UIColor(themeManager.selectedTheme.onBackground)
	]
	
	appearance.backgroundColor = UIColor(themeManager.selectedTheme.background)

	//In the following two lines you make sure that you apply the style for good
	UINavigationBar.appearance().scrollEdgeAppearance = appearance
	UINavigationBar.appearance().standardAppearance = appearance

	// This property is not present on the UINavigationBarAppearance
	// object for some reason and you have to leave it til the end
	UINavigationBar.appearance().tintColor = UIColor(themeManager.selectedTheme.onBackground)
}
