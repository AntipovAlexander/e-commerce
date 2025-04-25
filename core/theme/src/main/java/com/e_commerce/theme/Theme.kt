package com.e_commerce.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when (darkTheme) {
        true -> DarkColorScheme
        false -> LightColorScheme
    }

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppSpacings provides defaultSpacings,
        LocalAppShapes provides defaultShapes,
        LocalAppTypography provides defaultTypography,
        content = content
    )
}

object Theme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val spacings: AppSpacings
        @Composable
        @ReadOnlyComposable
        get() = LocalAppSpacings.current

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalAppShapes.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current
}