package com.e_commerce.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class AppShapes(
    val small: Shape,
    val default: Shape,
    val large: Shape,
)

val defaultShapes = AppShapes(
    small = RoundedCornerShape(size = 8.dp),
    default = RoundedCornerShape(size = 12.dp),
    large = RoundedCornerShape(size = 24.dp)
)

val LocalAppShapes = staticCompositionLocalOf { defaultShapes }