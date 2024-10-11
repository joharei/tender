//
//  ContentView.swift
//  Tender
//
//  Created by Johan Reitan on 11/10/2024.
//

import SwiftUI
import Shared

struct ContentView: View {
	let viewModel = CarcassesViewModelHelper().viewModel

    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text("Hello, world!")
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
