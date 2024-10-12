//
//  EditView.swift
//  Tender
//
//  Created by Johan Reitan on 11/10/2024.
//

import SwiftUI
import Shared

struct EditView: View {
	@State
	var carcass: CarcassUiState? = nil
	
    var body: some View {
        Text("\(carcass?.name ?? "No name")")
    }
}

#Preview {
    EditView()
}
