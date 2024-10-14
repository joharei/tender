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
	let onDeleteClick: (Int64) -> Void

    var body: some View {
		let carcassesTitle = MR.strings().carcasses_title.desc().localized()

		NavigationSplitView {
			List(uiState.carcasses, id: \.id) { carcass in
				NavigationLink {
					EditView(carcass: carcass)
				} label: {
					CarcassView(uiState: carcass) {
						onDeleteClick(carcass.id)
					}
				}
			}
			.navigationTitle(carcassesTitle)
		} detail: {
			EditView()
		}
    }
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
