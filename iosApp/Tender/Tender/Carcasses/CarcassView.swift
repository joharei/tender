//
//  CarcassView.swift
//  Tender
//
//  Created by Johan Reitan on 12/10/2024.
//

import Shared
import SwiftUI

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

                let status =
                    switch onEnum(of: uiState.status) {
                    case .done(let done):
                        getCarcassLabelDone(doneDailyDegrees: done.doneDailyDegrees).localized()
                    case .inProgress(let inProgress):
                        getCarcassLabelDailyDegrees(
                            dailyDegrees: inProgress.currentDailyDegrees.format(),
                            percent: Int32((inProgress.progress * 100).rounded())
                        ).localized()
                    }
                Text(status)
                    .font(themeManager.selectedTheme.titleSmall)
                    .foregroundStyle(themeManager.selectedTheme.onBackground)

                if let status = uiState.status as? CarcassUiStateStatusInProgress {
                    Spacer()
                        .frame(height: 24)

                    ProgressView(value: status.progress)
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
                            getCarcassDurationIn(durationUntilDueEstimate: status.durationUntilDueEstimate)
                                .localized()
                        )
                        .font(themeManager.selectedTheme.labelSmall)
                        .foregroundStyle(themeManager.selectedTheme.onBackground)
                    }
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
            status: CarcassUiStateStatusInProgress(
                durationUntilDueEstimate: Int64(10),
                progress: 0.5,
                currentDailyDegrees: 20,
            )
        )
    }
}

#Preview(traits: .sizeThatFitsLayout) {
    CarcassView(uiState: .preview, onDeleteClick: {})
        .environmentObject(ThemeManager())
}
