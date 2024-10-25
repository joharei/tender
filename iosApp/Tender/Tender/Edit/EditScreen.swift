//
//  EditView.swift
//  Tender
//
//  Created by Johan Reitan on 11/10/2024.
//

import Shared
import SwiftUI

struct EditScreen: View {
	let viewModel: EditViewModel
	@State
	var uiState: EditUiState

	init(carcassId: Int64? = nil) {
		viewModel = EditViewModelHelper().viewModel(carcassId: carcassId as NSNumber?)
		uiState = initialEditUiState()
	}

	var body: some View {
		EditView(uiState: $uiState, onUiEvent: viewModel.onUiEvent)
			.collect(flow: viewModel.uiState, into: $uiState)
	}
}

struct EditView: View {
	@EnvironmentObject
	private var themeManager: ThemeManager
	@Binding
	var uiState: EditUiState
	let onUiEvent: (EditUiEvent) -> Void
	@Environment(\.dismiss) var dismiss

	var body: some View {
		let title =
			switch uiState.editMode {
			case .addNew: MR.strings().edit_title_add
			case .edit: MR.strings().edit_title_edit
			}

		NavigationView {
			Form {
				Section {
					TextField(
						MR.strings().edit_label_name.desc().localized(),
						text: Binding<String>(
							get: { uiState.name ?? "" },
							set: { onUiEvent(.OnSetName(name: $0)) }
						),
						onCommit: {

						}
					)

					HStack {
						TextField(
							MR.strings().edit_label_lat.desc().localized(),
							text: Binding<String>(
								get: { uiState.lat ?? "" },
								set: { onUiEvent(.OnSetLat(lat: $0)) }
							)
						)
						TextField(
							MR.strings().edit_label_lon.desc().localized(),
							text: Binding<String>(
								get: { uiState.lon ?? "" },
								set: { onUiEvent(.OnSetLon(lon: $0)) }
							)
						)
					}

					DatePicker(
						MR.strings().edit_label_start_date.desc().localized(),
						selection: Binding<Date>(
							get: { uiState.startDateTime },
							set: {
								onUiEvent(.OnSetStartDate(startDate: Calendar.current.dateComponents([.day, .month, .year], from: $0)))

								onUiEvent(.OnSetStartTime(startTime: Calendar.current.dateComponents([.hour, .minute, .second, .nanosecond], from: $0)))
							}
						)
					)
				}
				.listRowBackground(themeManager.selectedTheme.surfaceContainerHighest)

				Section {
					LabeledContent {
						Text(String(uiState.dailyDegreesGoal))
					} label: {
						Text(resourceKey: \.edit_label_daily_degrees_goal)
					}
					Slider(
						value: Binding<Double>(
							get: { Double(uiState.dailyDegreesGoal) },
							set: { onUiEvent(.OnSetDailyDegreesGoal(dailyDegreesGoal: Int32($0))) }
						),
						in: 30...80,
						step: 1
					) {
						Text(resourceKey: \.edit_label_daily_degrees_goal)
					} minimumValueLabel: {
						Text("30")
					} maximumValueLabel: {
						Text("80")
					}
				}
				.listRowBackground(themeManager.selectedTheme.surfaceContainerHighest)

				Section {
					Button(resourceKey: \.button_save) {
						onUiEvent(.OnSave.shared)
					}
					.disabled(uiState.saveButtonEnabled == false)
				}
				.listRowBackground(
					uiState.saveButtonEnabled ? themeManager.selectedTheme.primary :
						themeManager.selectedTheme.onSurface.opacity(0.12))
				.foregroundStyle(
					uiState.saveButtonEnabled ? themeManager.selectedTheme.onPrimary :
						themeManager.selectedTheme.onSurface.opacity(0.38))
			}
			.navigationBarTitle(title.desc().localized())
			.onChange(of: uiState.saveCompleted) {
				if (uiState.saveCompleted) {
					dismiss()
				}
			}
			.scrollContentBackground(.hidden)
			.background(themeManager.selectedTheme.background)
			.foregroundStyle(themeManager.selectedTheme.onSurface)
			.tint(themeManager.selectedTheme.primary)
		}
		.scrollContentBackground(.hidden)
		.background(themeManager.selectedTheme.background)
	}
}

#Preview {
	@State
	@Previewable
	var uiState: EditUiState = initialEditUiState()

	let themeManager = ThemeManager()
	let _ = setupNavigationBarAppearance(themeManager: themeManager)

	EditView(uiState: $uiState, onUiEvent: { _ in })
		.environmentObject(themeManager)
}
