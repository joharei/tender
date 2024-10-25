//
//  ThemeProtocol.swift
//  Tender
//
//  Created by Johan Reitan on 22/10/2024.
//


import SwiftUI
/**
 Protocol for themes
 */
protocol ThemeProtocol {
 	var displayLarge: Font { get }
	var displayMedium: Font { get }
	var displaySmall: Font { get }
	var headlineLarge: Font { get }
	var headlineMedium: Font { get }
	var headlineSmall: Font { get }
	var titleLarge: Font { get }
	var titleMedium: Font { get }
	var titleSmall: Font { get }
	var bodyLarge: Font { get }
	var bodyMedium: Font { get }
	var bodySmall: Font { get }
	var labelLarge: Font { get }
	var labelMedium: Font { get }
	var labelSmall: Font { get }

	var primary: Color { get }
	var onPrimary: Color { get }
	var primaryContainer: Color { get }
	var onPrimaryContainer: Color { get }
	var secondary: Color { get }
	var onSecondary: Color { get }
	var secondaryContainer: Color { get }
	var onSecondaryContainer: Color { get }
	var tertiary: Color { get }
	var onTertiary: Color { get }
	var tertiaryContainer: Color { get }
	var onTertiaryContainer: Color { get }
	var error: Color { get }
	var onError: Color { get }
	var errorContainer: Color { get }
	var onErrorContainer: Color { get }
	var background: Color { get }
	var onBackground: Color { get }
	var surface: Color { get }
	var onSurface: Color { get }
	var surfaceVariant: Color { get }
	var onSurfaceVariant: Color { get }
	var outline: Color { get }
	var outlineVariant: Color { get }
	var scrim: Color { get }
	var inverseSurface: Color { get }
	var inverseOnSurface: Color { get }
	var inversePrimary: Color { get }
	var surfaceDim: Color { get }
	var surfaceBright: Color { get }
	var surfaceContainerLowest: Color { get }
	var surfaceContainerLow: Color { get }
	var surfaceContainer: Color { get }
	var surfaceContainerHigh: Color { get }
	var surfaceContainerHighest: Color { get }
}
