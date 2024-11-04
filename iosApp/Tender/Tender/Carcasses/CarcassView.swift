//
//  CarcassView.swift
//  Tender
//
//  Created by Johan Reitan on 12/10/2024.
//

import SwiftUI
import Shared

struct CarcassView: View {
	@EnvironmentObject
	private var themeManager: ThemeManager
	let uiState: CarcassUiState
	let onDeleteClick: () -> Void

	@State
	private var showDeleteConfirmation: Bool = false

	var body: some View {
		VStack {
			VStack(alignment: .leading) {
				Text(uiState.name)
					.font(themeManager.selectedTheme.headlineMedium)
					.foregroundStyle(themeManager.selectedTheme.onBackground)

				Text(
					getCarcassLabelDailyDegrees(
						dailyDegrees: uiState.current24HoursDegrees.format(),
						percent: Int32((uiState.progress * 100).rounded())
					).localized()
				)
				.font(themeManager.selectedTheme.titleSmall)
				.foregroundStyle(themeManager.selectedTheme.onBackground)

				Spacer()
					.frame(height: 24)

				ProgressView(value: uiState.progress)
					.tint(themeManager.selectedTheme.primary)
					.background(themeManager.selectedTheme.secondaryContainer)
				HStack {
					Text(
						getCarcassDurationAgo(durationSinceStarted: uiState.durationSinceStarted)
							.localized()
					)
					.font(themeManager.selectedTheme.labelSmall)
					.foregroundStyle(themeManager.selectedTheme.onBackground)

					Spacer()

					Text(
						getCarcassDurationIn(durationUntilDueEstimate: uiState.durationUntilDueEstimate)
							.localized()
					)
					.font(themeManager.selectedTheme.labelSmall)
					.foregroundStyle(themeManager.selectedTheme.onBackground)
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
					.foregroundStyle(themeManager.selectedTheme.error)
					.alert(isPresented: $showDeleteConfirmation) {
						Alert(
							title: Text(getCarcassLabelConfirmDelete(name: uiState.name).localized()),
							primaryButton: .destructive(Text(resourceKey: \.button_delete), action: onDeleteClick),
							secondaryButton: .cancel(Text(resourceKey: \.button_cancel)) {
								showDeleteConfirmation = false
							}
						)
					}
				}
			}
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
		.environmentObject(ThemeManager())
}
