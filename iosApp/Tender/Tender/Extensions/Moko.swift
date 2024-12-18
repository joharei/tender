//
//  Moko.swift
//  Tender
//
//  Created by Johan Reitan on 14/10/2024.
//

import SwiftUI
import Shared

extension StringResource {
	func localized() -> String {
		return desc().localized()
	}
}

//extension Image {
//	init(resourceKey: KeyPath<MR.images, shared.ImageResource>) {
//		self.init(uiImage: MR.images()[keyPath: resourceKey].toUIImage()!)
//	}
//}

extension Text {
	init(resourceKey: KeyPath<MR.strings, StringResource>) {
		self.init(MR.strings()[keyPath: resourceKey].localized())
	}
}

extension Label where Title == Text, Icon == Image {
	init(resourceKey: KeyPath<MR.strings, StringResource>, systemImage: String) {
		self.init(MR.strings()[keyPath: resourceKey].localized(), systemImage: systemImage)
	}
}

extension Button where Label == Text {
	init(resourceKey: KeyPath<MR.strings, StringResource>, action: @escaping () -> Void) {
		self.init(MR.strings()[keyPath: resourceKey].localized(), action: action)
	}

	init(resourceKey: KeyPath<MR.strings, StringResource>, role: ButtonRole?, action: @escaping () -> Void) {
		self.init(MR.strings()[keyPath: resourceKey].localized(), role: role, action: action)
	}
}

extension Font {
	init(resource: KeyPath<MR.fonts, FontResource>, withSize: Double = 14.0) {
		self.init(MR.fonts()[keyPath: resource].uiFont(withSize: withSize))
    }
}
