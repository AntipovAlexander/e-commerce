package com.e_commerce.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppSpacings(
    val tiny: Dp,
    val small: Dp,
    val default: Dp,
    val large: Dp,
    val xLarge: Dp,
)

val defaultSpacings = AppSpacings(
    tiny = 4.dp,
    small = 8.dp,
    default = 16.dp,
    large = 24.dp,
    xLarge = 32.dp,
)

val LocalAppSpacings = staticCompositionLocalOf { defaultSpacings }