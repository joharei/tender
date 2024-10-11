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
	init() {
		KoinKt.doInitKoin()
	}

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
