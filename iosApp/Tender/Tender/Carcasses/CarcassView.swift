//
//  CarcassView.swift
//  Tender
//
//  Created by Johan Reitan on 12/10/2024.
//

import SwiftUI
import Shared

struct CarcassView: View {
	let uiState: CarcassUiState
	let onDeleteClick: () -> Void

	@State
	private var showDeleteConfirmation: Bool = false

	var body: some View {
		VStack {
			VStack(alignment: .leading) {
				Text(uiState.name)
					.font(.title)

				Text(
					getCarcassLabelDailyDegrees(
						dailyDegrees: uiState.current24HoursDegrees.format(),
						percent: Int32((uiState.progress * 100).rounded())
					).localized()
				)
					.font(.title3)

				Spacer()
					.frame(height: 24)

				ProgressView(value: uiState.progress)
				HStack {
					Text(
						getCarcassDurationAgo(durationSinceStarted: uiState.durationSinceStarted)
							.localized()
					)
						.font(.caption)

					Spacer()

					Text(
						getCarcassDurationIn(durationUntilDueEstimate: uiState.durationUntilDueEstimate)
							.localized()
					)
						.font(.caption)
				}

				Spacer()
					.frame(height: 16)

				HStack {
					Spacer()
					Button(role: .destructive) {
						showDeleteConfirmation = true
					} label: {
						Image(systemName: "trash")
					}
					.buttonStyle(.borderless)
					.alert(isPresented: $showDeleteConfirmation) {
						Alert(
							title: Text(getCarcassLabelConfirmDelete(name: uiState.name).localized()),
							primaryButton: .destructive(Text(resourceKey: \.button_delete), action: onDeleteClick),
							secondaryButton: .default(Text(resourceKey: \.button_cancel)) {
								showDeleteConfirmation = false
							}
						)
					}
				}
			}
			.padding(16)
		}
	}
}

extension CarcassUiState {
	static var preview: CarcassUiState {
		.init(
			id: Int64(1),
			name: "Carcass",
			durationSinceStarted: Int64(10),
			durationUntilDueEstimate: Int64(10),
			progress: 0.5,
			current24HoursDegrees: 20
		)
	}
}

#Preview(traits: .sizeThatFitsLayout) {
	CarcassView(uiState: .preview, onDeleteClick: {})
}
