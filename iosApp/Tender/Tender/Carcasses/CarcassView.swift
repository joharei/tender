//
//  CarcassView.swift
//  Tender
//
//  Created by Johan Reitan on 12/10/2024.
//

import SwiftUI
import Shared

struct CarcassView: View {
	@Binding
	var uiState: CarcassUiState

    var body: some View {
		VStack {
			VStack {
				Text(uiState.name)
					.font(.subheadline)
			}
			.padding(16)
		}
    }
}

extension CarcassUiState {
	static var preview: CarcassUiState { .init(
		id: Int64(1),
		name: "Carcass",
		durationSinceStarted: Int64(10),
		durationUntilDueEstimate: Int64(10),
		progress: 0.5,
		current24HoursDegrees: 20
	) }
}

#Preview(traits: .sizeThatFitsLayout) {
	CarcassView(uiState: .constant(.preview))
}
