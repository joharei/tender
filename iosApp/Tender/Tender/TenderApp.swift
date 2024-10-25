//
//  TenderApp.swift
//  Tender
//
//  Created by Johan Reitan on 11/10/2024.
//

import SwiftUI
import Shared

@main
struct TenderApp: App {
	@ObservedObject var themeManager = ThemeManager()
	
	init() {
		doInitKoin()
		
		setupNavigationBarAppearance(themeManager: themeManager)
	}

    var body: some Scene {
        WindowGroup {
            CarcassesScreen()
				.environmentObject(themeManager)
        }
    }
}
