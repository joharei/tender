//
//  ContentView.swift
//  Tender
//
//  Created by Johan Reitan on 11/10/2024.
//

import SwiftUI
import Shared

struct CarcassesScreen: View {
	let viewModel = CarcassesViewModelHelper().viewModel
	@State
	var uiState: CarcassesUiState = .initial

	var body: some View {
		CarcassesView(uiState: $uiState, onDeleteClick: viewModel.deleteCarcass)
			.collect(flow: viewModel.uiState, into: $uiState)
	}
}

struct CarcassesView: View {
	@Binding
	var uiState: CarcassesUiState
	@State
	var sheetState: SheetState?
	let onDeleteClick: (Int64) -> Void

	var body: some View {
		let carcassesTitle = MR.strings().carcasses_title.desc().localized()

		NavigationStack {
			List(uiState.carcasses, id: \.id) { carcass in
				CarcassView(uiState: carcass) {
					onDeleteClick(carcass.id)
				}
				.onTapGesture {
					sheetState = .edit(carcass)
				}
			}
			.navigationTitle(carcassesTitle)
			.toolbar {
				ToolbarItem {
					Button {
						sheetState = .add
					} label: {
						Label(resourceKey: \.carcasses_button_add_new, systemImage: "plus")
					}
				}
			}
			.sheet(item: $sheetState, onDismiss: {
				sheetState = nil
			}) { item in
				switch item {
				case .add:
					EditScreen()
				case .edit(let carcass):
					EditScreen(carcassId: carcass.id)
				}
			}
		}
	}
}

enum SheetState: Hashable, Identifiable {
	var id: Self {
		return self
	}

	case edit(CarcassUiState)
	case add
}

extension CarcassesUiState {
	static var initial: Self {
		return Self(carcasses: [], loading: true)
	}

	static var sample: Self {
		var carcasses = [CarcassUiState]()
		for index in 0..<7 {
			carcasses.append(CarcassUiState(
				id: Int64(index),
				name: "Carcass \(index)",
				durationSinceStarted: Int64(10),
				durationUntilDueEstimate: Int64(10),
				progress: 0.5,
				current24HoursDegrees: 20
			))
		}
		return Self(
			carcasses: carcasses,
			loading: false
		)
	}
}

#Preview {
	@State
	@Previewable
	var uiState: CarcassesUiState = .sample

	return CarcassesView(uiState: $uiState, onDeleteClick: { _ in })
}
